//package com.bank.digital_procurement.model;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.apache.catalina.Manager;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "departments")
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Department {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = true)
//    private String name;
//
//    @Column(unique = true, nullable = true)
//    private String code;
//
//    private String description;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Department parent;
//
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Department> children = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manager_id")
//    private Manager manager;
//
//    // Budget limit for this department
//    @Column(name = "budget_limit", precision = 15, scale = 2)
//    private BigDecimal budgetLimit;
//
//    @Column(name = "spent_amount", precision = 15, scale = 2)
//    private BigDecimal spentAmount = BigDecimal.ZERO;
//
//    @Enumerated(EnumType.STRING)
//    private DepartmentStatus status = DepartmentStatus.ACTIVE;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    // Users belonging to this department
//    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
//    private Set<User> users = new HashSet<>();
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    // Helper methods
//    public BigDecimal getRemainingBudget() {
//        if (budgetLimit == null) return BigDecimal.ZERO;
//        return budgetLimit.subtract(spentAmount != null ? spentAmount : BigDecimal.ZERO);
//    }
//
//    public boolean isRootDepartment() {
//        return parent == null;
//    }
//
//    public boolean hasChildren() {
//        return children != null && !children.isEmpty();
//    }
//
//
//}
