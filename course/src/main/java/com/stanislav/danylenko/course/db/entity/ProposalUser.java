package com.stanislav.danylenko.course.db.entity;

import com.stanislav.danylenko.course.db.entity.bl.Report;
import com.stanislav.danylenko.course.db.entity.pk.ProposalUserPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProposalUserPK.class)
public class ProposalUser implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    private Report report;

}
