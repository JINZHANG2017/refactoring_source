package refactoring.performancebill.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.performancebill.domain.model.perfbill.PerfBill;
import refactoring.performancebill.domain.model.perfbill.PerfBillRepository;

public interface PerfBillRepositoryJpa
        extends JpaRepository<PerfBill, Long>, PerfBillRepository {
}
