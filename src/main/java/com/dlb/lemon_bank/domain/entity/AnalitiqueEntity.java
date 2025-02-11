package com.dlb.lemon_bank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "analytique")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AnalitiqueEntity {

    @Id
    @SequenceGenerator(name = "analitique_seq",sequenceName = "analitique_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analitique_seq")
    private Integer id;
    @Column(name = "type_")
    private String type;
    @Column(name = "currency_")
    private String currency;
    @Column(name = "count_")
    private Integer count;
    @Column(name = "date_")
    private LocalDateTime date;

}
