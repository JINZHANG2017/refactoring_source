package refactoring.performancebill.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import refactoring.performancebill.domain.model.perfbill.PerfBill;
import refactoring.performancebill.domain.model.perfbill.PerfBillRepository;
import refactoring.performancebill.domain.model.perfsummary.PerfSummary;
import refactoring.performancebill.domain.model.play.Play;
import refactoring.performancebill.domain.model.play.PlayRepository;

// Done 1场表演_悲剧_不大于30人
// Done 1场表演_悲剧_大于30人
// Done 1场表演_喜剧_不大于20人
// Done 1场表演_喜剧_大于20人
// Done 没有表演

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PerfBillServiceTest {

    @Mock
    private PerfBillRepository mockBillRepository;

    @Mock
    PlayRepository mockPlayRepository;

    @InjectMocks
    PerfBillService service;

    static final Play HAMLET = new Play("hamlet"
            , "Hamlet"
            , "tragedy");
    static final Play AS_LIKE = new Play("as-like"
            , "As You Like It"
            , "comedy");
    static final Play OTHELLO = new Play("othello"
            , "Othello"
            , "tragedy");

    @Test
    void createBill_1场表演_悲剧_不大于30人() {
        doReturn(HAMLET).when(mockPlayRepository).findById("hamlet");
        verifyCreateBillForOnePerformance(
                "hamlet"
                , 10
                , 40000
                , 0
                , "Hamlet");
    }

    @Test
    void createBill_1场表演_悲剧_大于30人() {
        doReturn(OTHELLO).when(mockPlayRepository).findById("othello");
        verifyCreateBillForOnePerformance(
                "othello"
                , 50
                , 60000
                , 20
                , "Othello");


    }

    @Test
    void createBill_1场表演_喜剧_不大于20人() {
        doReturn(AS_LIKE).when(mockPlayRepository).findById("as-like");
        verifyCreateBillForOnePerformance(
                "as-like"
                , 10
                , 33000
                , 2
                , "As You Like It");


    }

    @Test
    void createBill_1场表演_喜剧_大于20人() {
        doReturn(AS_LIKE).when(mockPlayRepository).findById("as-like");

        verifyCreateBillForOnePerformance(
                "as-like"
                , 30
                , 54000
                , 6
                , "As You Like It");


    }


    private void verifyCreateBillForOnePerformance(
            String playId
            , int audience
            , int expectedAmount
            , int expectedVolumeCredits
            , String expectedPlayName) {

        final String company = "AAA";

        PerfSummary summary = new PerfSummary(company);
        summary.addPerformance(playId, audience);

        service.createBill(summary);

        ArgumentCaptor<PerfBill> argument = ArgumentCaptor.forClass(PerfBill.class);
        verify(mockBillRepository).save(argument.capture());
        PerfBill actual = argument.getValue();

        //Approach 1
        assertThat(actual.getCustomer()).isEqualTo(company);
        assertThat(actual.getVolumeCredits()).isEqualTo(expectedVolumeCredits);
        assertThat(actual.getItems()).hasSize(1)
                .extracting("name", "amount", "audience")
                .contains(tuple(expectedPlayName, expectedAmount, audience));

        // Approach 2
//        PerformanceBill expected = new PerformanceBill(company)
//                .setTotalAmount(expectedAmount)
//                .setVolumeCredits(expectedVolumeCredits)
//                .addItem(expectedPlayName, expectedAmount, audience);
//
//        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}