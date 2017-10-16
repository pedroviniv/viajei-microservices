/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author pafer
 */

@Embeddable
public class Period implements Serializable {
    
    
    private LocalDateTime start;
    private LocalDateTime end;

    public Period(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public Period() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    
    public boolean hasConflict(Period period) {
        return true;
    }
    
    public int getDays() {
        //parse to date
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        //get period
        java.time.Period period = java.time.Period
                .between(startDate, endDate);
        //return days qty
        return period.getDays();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.start);
        hash = 31 * hash + Objects.hashCode(this.end);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Period other = (Period) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Period{" + "start=" + start + ", end=" + end + '}';
    }
}
