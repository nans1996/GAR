/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findByIDMessage", query = "SELECT m FROM Message m WHERE m.iDMessage = :iDMessage")
    , @NamedQuery(name = "Message.findBySubject", query = "SELECT m FROM Message m WHERE m.subject = :subject")
    , @NamedQuery(name = "Message.findByDate", query = "SELECT m FROM Message m WHERE m.date = :date")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Message")
    private Integer iDMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    @Size(max = 65535)
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "ID_Topic", referencedColumnName = "ID_Topic")
    @ManyToOne(optional = false)
    private Topic iDTopic;
    @JoinColumn(name = "ID_User", referencedColumnName = "ID_User")
    @ManyToOne(optional = false)
    private User iDUser;

    public Message() {
    }

    public Message(Integer iDMessage) {
        this.iDMessage = iDMessage;
    }

    public Message(Integer iDMessage, String subject, Date date) {
        this.iDMessage = iDMessage;
        this.subject = subject;
        this.date = date;
    }

    public Integer getIDMessage() {
        return iDMessage;
    }

    public void setIDMessage(Integer iDMessage) {
        this.iDMessage = iDMessage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Topic getIDTopic() {
        return iDTopic;
    }

    public void setIDTopic(Topic iDTopic) {
        this.iDTopic = iDTopic;
    }

    public User getIDUser() {
        return iDUser;
    }

    public void setIDUser(User iDUser) {
        this.iDUser = iDUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDMessage != null ? iDMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.iDMessage == null && other.iDMessage != null) || (this.iDMessage != null && !this.iDMessage.equals(other.iDMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Message[ iDMessage=" + iDMessage + " ]";
    }
    
}
