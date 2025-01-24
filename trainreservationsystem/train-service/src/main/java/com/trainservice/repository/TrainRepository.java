//package com.trainservice.repository;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//import com.trainservice.model.TrainDetails;
//
//@Repository
//public interface TrainRepository  extends MongoRepository<TrainDetails,String>{
//  
//	List<TrainDetails> findBySourceStationAndDestinationStationAndTrainDepatureDate(String sourceStation, String destinationStation, Date trainDepatureDate);
//	
//    Optional<TrainDetails> findByTrainNumber(int trainNumber);
//}

package com.trainservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.trainservice.model.TrainDetails;

@Repository
public interface TrainRepository  extends MongoRepository<TrainDetails,String>{
  
    List<TrainDetails> findBySourceStationAndDestinationStationAndTrainDepatureDate(String sourceStation, String destinationStation, Date trainDepatureDate);
    
    Optional<TrainDetails> findByTrainNumber(int trainNumber);

    List<TrainDetails> findByTrainDepatureDate(String trainDepatureDate);

    List<TrainDetails> findBySourceStation(String sourceStation);
}