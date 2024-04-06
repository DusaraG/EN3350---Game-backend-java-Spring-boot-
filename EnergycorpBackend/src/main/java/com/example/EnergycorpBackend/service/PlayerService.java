package com.example.EnergycorpBackend.service;

import com.example.EnergycorpBackend.dao.PlayerDao;
import com.example.EnergycorpBackend.dto.ApiKey;
import com.example.EnergycorpBackend.dto.Feedback;
import com.example.EnergycorpBackend.dto.JwtToken;
import com.example.EnergycorpBackend.dto.Results;
import com.example.EnergycorpBackend.globalVariables.CurrentJwt;
import com.example.EnergycorpBackend.globalVariables.QuestionnaireFeedbacks;
import com.example.EnergycorpBackend.repository.SavedAnswers;
import org.modelmapper.ModelMapper;
import com.example.EnergycorpBackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerService {
    @Autowired
    SavedAnswers attempt;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    ModelMapper mapper;
    WebClient webClient = WebClient.create("http://20.15.114.131:8080");

    public void addAuthorization(WebClient client){
        client.get().headers(h -> h.setBearerAuth(CurrentJwt.currentJwt));

    }

    public boolean testConnection(){
        try{
            webClient.get().uri("/api/user/profile/view")
                    .header("Authorization", "Bearer " + CurrentJwt.currentJwt)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }
    public String getNewJwt() {
        ApiKey apiKey = new ApiKey();

        JwtToken response = webClient.post()
                .uri("/api/login")
                .body(Mono.just(apiKey), ApiKey.class)
                .retrieve()
                .bodyToMono(JwtToken.class)
                .block();
        CurrentJwt.currentJwt = response.getJwt();
        return response.getJwt();
    }
    public  boolean validateJwt(String jwt){
        return  false;
    }
    public boolean hasAttempted(Integer playerNo) {
        if(playerRepo.findById(playerNo).isPresent())
            return playerRepo.findById(playerNo).get().isAttempted();
        return false;
    }

    public void resetPlayer(Integer playerID){
        if(playerRepo.findById(playerID).isPresent()){
            playerRepo.deleteById(playerID);
            PlayerDao dao = new PlayerDao(playerID,false);
            playerRepo.save(dao);}
    }
    public Integer insert_results(Results results) {
        PlayerDao dao = this.mapper.map(results, PlayerDao.class);
        var total = 0;
        if (dao.getQ01() == 3) {total++; dao.setA1(true);}else dao.setA1(false);
        if (dao.getQ02() == 2) {total++; dao.setA2(true);}else dao.setA2(false);
        if (dao.getQ03() == 3) {total++; dao.setA3(true);}else dao.setA3(false);
        if (dao.getQ04() == 2) {total++; dao.setA4(true);}else dao.setA4(false);
        if (dao.getQ05() == 3) {total++; dao.setA5(true);}else dao.setA5(false);
        if (dao.getQ06() == 3) {total++; dao.setA6(true);}else dao.setA6(false);
        if (dao.getQ07() == 2) {total++; dao.setA7(true);}else dao.setA7(false);
        if (dao.getQ08() == 3) {total++; dao.setA8(true);}else dao.setA8(false);
        if (dao.getQ09() == 3) {total++; dao.setA9(true);}else dao.setA9(false);
        if (dao.getQ10() == 3) {total++; dao.setA10(true);}else dao.setA10(false);
        dao.setTotal(total);
        dao.setAttempted(true);
        playerRepo.save(dao);
        return total;
    }
    public List<Feedback> getResults(Integer id){
        PlayerDao x;
        if(playerRepo.existsById(id))  x= playerRepo.findById(id).get();
        else throw new RuntimeException("No player by that id");

        List<Feedback> y= new ArrayList<Feedback>();
        Feedback z1 = new Feedback();
        Feedback z2 = new Feedback();
        Feedback z3 = new Feedback();
        Feedback z4 = new Feedback();
        Feedback z5 = new Feedback();
        Feedback z6 = new Feedback();
        Feedback z7 = new Feedback();
        Feedback z8 = new Feedback();
        Feedback z9 = new Feedback();
        Feedback z10 = new Feedback();
        z1.setQuestionNo(1);
        z1.setCorrect(x.isA1());
        z1.setGeneralFeedback(QuestionnaireFeedbacks.f1);
        switch (x.getQ01()) {
            case 1: z1.setSpecificFeedback(QuestionnaireFeedbacks.f1a);
            case 2: z1.setSpecificFeedback(QuestionnaireFeedbacks.f1b);
            case 3: z1.setSpecificFeedback(QuestionnaireFeedbacks.f1c);
            case 4: z1.setSpecificFeedback(QuestionnaireFeedbacks.f1d);
        }
        y.add(z1);

        z2.setQuestionNo(2);
        z2.setCorrect(x.isA2());
        z2.setGeneralFeedback(QuestionnaireFeedbacks.f2);
        switch (x.getQ02()) {
            case 1: z2.setSpecificFeedback(QuestionnaireFeedbacks.f2a);
            case 2: z2.setSpecificFeedback(QuestionnaireFeedbacks.f2b);
            case 3: z2.setSpecificFeedback(QuestionnaireFeedbacks.f2c);
            case 4: z2.setSpecificFeedback(QuestionnaireFeedbacks.f2d);
        }
        y.add(z2);

        z3.setQuestionNo(3);
        z3.setCorrect(x.isA3());
        z3.setGeneralFeedback(QuestionnaireFeedbacks.f3);
        switch (x.getQ03()) {
            case 1: z3.setSpecificFeedback(QuestionnaireFeedbacks.f3a);
            case 2: z3.setSpecificFeedback(QuestionnaireFeedbacks.f3b);
            case 3: z3.setSpecificFeedback(QuestionnaireFeedbacks.f3c);
            case 4: z3.setSpecificFeedback(QuestionnaireFeedbacks.f3d);
        }
        y.add(z3);

        z4.setQuestionNo(4);
        z4.setCorrect(x.isA4());
        z4.setGeneralFeedback(QuestionnaireFeedbacks.f4);
        switch (x.getQ04()) {
            case 1: z4.setSpecificFeedback(QuestionnaireFeedbacks.f4a);
            case 2: z4.setSpecificFeedback(QuestionnaireFeedbacks.f4b);
            case 3: z4.setSpecificFeedback(QuestionnaireFeedbacks.f4c);
            case 4: z4.setSpecificFeedback(QuestionnaireFeedbacks.f4d);
        }
        y.add(z4);

        z5.setQuestionNo(5);
        z5.setCorrect(x.isA5());
        z5.setGeneralFeedback(QuestionnaireFeedbacks.f5);
        switch (x.getQ05()) {
            case 1: z5.setSpecificFeedback(QuestionnaireFeedbacks.f5a);
            case 2: z5.setSpecificFeedback(QuestionnaireFeedbacks.f5b);
            case 3: z5.setSpecificFeedback(QuestionnaireFeedbacks.f5c);
            case 4: z5.setSpecificFeedback(QuestionnaireFeedbacks.f5d);
        }
        y.add(z5);

        z6.setQuestionNo(6);
        z6.setCorrect(x.isA6());
        z6.setGeneralFeedback(QuestionnaireFeedbacks.f6);
        switch (x.getQ06()) {
            case 1: z6.setSpecificFeedback(QuestionnaireFeedbacks.f6a);
            case 2: z6.setSpecificFeedback(QuestionnaireFeedbacks.f6b);
            case 3: z6.setSpecificFeedback(QuestionnaireFeedbacks.f6c);
            case 4: z6.setSpecificFeedback(QuestionnaireFeedbacks.f6d);
        }
        y.add(z6);

        z7.setQuestionNo(7);
        z7.setCorrect(x.isA7());
        z7.setGeneralFeedback(QuestionnaireFeedbacks.f7);
        switch (x.getQ07()) {
            case 1: z7.setSpecificFeedback(QuestionnaireFeedbacks.f7a);
            case 2: z7.setSpecificFeedback(QuestionnaireFeedbacks.f7b);
            case 3: z7.setSpecificFeedback(QuestionnaireFeedbacks.f7c);
            case 4: z7.setSpecificFeedback(QuestionnaireFeedbacks.f7d);
        }
        y.add(z7);

        z8.setQuestionNo(8);
        z8.setCorrect(x.isA8());
        z8.setGeneralFeedback(QuestionnaireFeedbacks.f8);
        switch (x.getQ08()) {
            case 1: z8.setSpecificFeedback(QuestionnaireFeedbacks.f8a);
            case 2: z8.setSpecificFeedback(QuestionnaireFeedbacks.f8b);
            case 3: z8.setSpecificFeedback(QuestionnaireFeedbacks.f8c);
            case 4: z8.setSpecificFeedback(QuestionnaireFeedbacks.f8d);
        }
        y.add(z8);

        z9.setQuestionNo(9);
        z9.setCorrect(x.isA9());
        z9.setGeneralFeedback(QuestionnaireFeedbacks.f9);
        switch (x.getQ09()) {
            case 1: z9.setSpecificFeedback(QuestionnaireFeedbacks.f9a);
            case 2: z9.setSpecificFeedback(QuestionnaireFeedbacks.f9b);
            case 3: z9.setSpecificFeedback(QuestionnaireFeedbacks.f9c);
            case 4: z9.setSpecificFeedback(QuestionnaireFeedbacks.f9d);
        }
        y.add(z9);

        z10.setQuestionNo(10);
        z10.setCorrect(x.isA10());
        z10.setGeneralFeedback(QuestionnaireFeedbacks.f10);
        switch (x.getQ10()) {
            case 1: z10.setSpecificFeedback(QuestionnaireFeedbacks.f10a);
            case 2: z10.setSpecificFeedback(QuestionnaireFeedbacks.f10b);
            case 3: z10.setSpecificFeedback(QuestionnaireFeedbacks.f10c);
            case 4: z10.setSpecificFeedback(QuestionnaireFeedbacks.f10d);
        }
        y.add(z10);
        return y;
    }

}

