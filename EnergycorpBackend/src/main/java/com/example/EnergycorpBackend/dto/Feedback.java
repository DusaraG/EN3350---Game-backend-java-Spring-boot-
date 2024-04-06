package com.example.EnergycorpBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Feedback {
    Integer questionNo;
    boolean correct;
    String generalFeedback;
    String specificFeedback;
}
