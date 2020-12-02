package refactoring.performancebill.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import refactoring.performancebill.domain.model.perfbill.PerfBill;
import refactoring.performancebill.domain.model.perfbill.PerfBillRepository;
import refactoring.performancebill.domain.model.perfsummary.Perf;
import refactoring.performancebill.domain.model.perfsummary.PerfSummary;
import refactoring.performancebill.domain.model.play.Play;
import refactoring.performancebill.domain.model.play.PlayRepository;

import javax.transaction.Transactional;

@Service
public class PerfBillService {
    @Autowired
    PerfBillRepository repository;

    @Autowired
    PlayRepository playRepository;

    @Transactional
    public PerfBill createBill(@RequestBody PerfSummary perfSummary) {
        PerfBill bill;

        int totalAmount = 0;
        int volumeCredits = 0;

        bill = new PerfBill(perfSummary.getCustomer());

        for (Perf perf : perfSummary.getPerfs()) {
            Play play = playRepository.findById(perf.getPlayId());
            int thisAmount;

            if (play.getType().equals("tragedy")) {
                thisAmount = 40000;
                if (perf.getAudience() > 30) {
                    thisAmount += 1000 * (perf.getAudience() - 30);
                }
            } else if (play.getType().equals("comedy")) {
                thisAmount = 30000;
                if (perf.getAudience() > 20) {
                    thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                }
                thisAmount += 300 * perf.getAudience();
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            int thisCredits = 0;
            if ("comedy".equals(play.getType())) {
                thisCredits = Math.max(perf.getAudience() - 30, 0);
                thisCredits += Math.floorDiv(perf.getAudience(), 5);
            } else {
                thisCredits = Math.max(perf.getAudience() - 30, 0);
            }

            volumeCredits += thisCredits;
            totalAmount += thisAmount;

            bill.addItem(play.getName(), thisAmount, perf.getAudience());


        }

        bill.setTotalAmount(totalAmount);
        bill.setVolumeCredits(volumeCredits);

        return repository.save(bill);
    }

}
