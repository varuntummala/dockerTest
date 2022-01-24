package com.springBoot.userDetails.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_table")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class User implements Serializable {

    @Id
    @Column(name = "USERID")
    private String userId;
    @Column(name = "USERNAME")
    private String userName;
    private String password;
    @Column(name = "ONLINESTATUS1")
    private String onlineStatus;
    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;
    @Column(name = "BIRTHDATE")
    private Date birthDate;

//    @ManyToOne(targetEntity = Organization.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "ORG_ID", referencedColumnName = "ORGID")

//    private Organization organization;

    @Column(name = "ORG_ID")
    private String org_Id;
//

}
