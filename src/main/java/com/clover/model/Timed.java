package com.clover.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by lkl on 2017/3/18.
 */
@Entity
@Data
@NoArgsConstructor
public class Timed {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    private Integer timedDay;
    private Integer timedWeek;
    private Integer timedMonth;
    private Long timedTotal;

}
