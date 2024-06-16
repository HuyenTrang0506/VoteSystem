package net.codejava.entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "involces")
@Setter
@Getter


public class Involce {

    @Id
    @Column(unique = true, nullable = false)
    private String invoiceId;

    private LocalDate date;
    private LocalDate dueDate;
    private String fullname;
    private String email;
    private double totalAmount;
    private String bankAccountNumber;

    // Constructors, getters, setters, equals, and hashCode

    public Involce() {}

    public Involce(String invoiceId, LocalDate date, LocalDate dueDate, String fullname, String email, double totalAmount, String bankAccountNumber) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.dueDate = dueDate;
        this.fullname = fullname;
        this.email = email;
        this.totalAmount = totalAmount;
        this.bankAccountNumber = bankAccountNumber;
    }


    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Involce invoice = (Involce) o;
        return Double.compare(invoice.totalAmount, totalAmount) == 0 &&  Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(date, invoice.date) && Objects.equals(dueDate, invoice.dueDate) && Objects.equals(fullname, invoice.fullname) && Objects.equals(email, invoice.email) && Objects.equals(bankAccountNumber, invoice.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash( invoiceId, date, dueDate, fullname, email, totalAmount, bankAccountNumber);
    }
}
