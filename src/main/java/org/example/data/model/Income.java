package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.boot.autoconfigure.web.WebProperties;


@Data
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String amount;
    @OneToOne
    private Category category;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private ExpensesTrackerApp expensesTrackerApp;

}
