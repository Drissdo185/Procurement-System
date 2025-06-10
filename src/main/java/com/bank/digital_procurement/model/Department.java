package com.bank.digital_procurement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Manager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    // Parent-Child relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Department parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Department> children = new HashSet<>();

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    // Budget fields
    @Column(name = "budget_limit", precision = 15, scale = 2)
    private BigDecimal budgetLimit;

    @Column(name = "spent_amount", precision = 15, scale = 2)
    private BigDecimal spentAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private DepartmentStatus status = DepartmentStatus.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<User> employees = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public BigDecimal getRemainingBudget() {
        if (budgetLimit == null) return BigDecimal.ZERO;
        return budgetLimit.subtract(spentAmount != null ? spentAmount : BigDecimal.ZERO);
    }

    public boolean isRootDepartment() {
        return parent == null;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }
}