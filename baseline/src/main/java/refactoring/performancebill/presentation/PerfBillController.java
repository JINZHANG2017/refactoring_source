package refactoring.performancebill.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import refactoring.performancebill.application.PerfBillService;
import refactoring.performancebill.domain.model.perfsummary.PerfSummary;
import refactoring.performancebill.domain.model.perfbill.PerfBill;

@RestController
public class PerfBillController {

    @Autowired
    private PerfBillService service;

    @PostMapping("/api/performancebill")
    public PerfBill createBill(@RequestBody PerfSummary perfSummary) {
        return service.createBill(perfSummary);
    }

}
