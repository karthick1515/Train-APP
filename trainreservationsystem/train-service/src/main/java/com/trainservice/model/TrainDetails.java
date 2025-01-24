package com.trainservice.model;


import java.util.Date;
import java.util.Map;
import com.trainservice.enums.BookingClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "trainCollection")
public class TrainDetails {
	@Id
	@NotNull(message = "Train number is required")
	private int trainNumber;
	@NotBlank(message = "Train name is required")
	private String trainName;

	@NotBlank(message = "Source station is required")
	private String sourceStation;
	@NotBlank(message = "Destination station is required")
	private String destinationStation;
	@NotBlank(message = "Departure time is required")
	private String departureTime;
	@NotBlank(message = "Arrival time is required")
	private String arrivalTime;

	@NotNull(message = "Train  depature date is required")
	private Date trainDepatureDate;
	@NotNull(message = "Train  arrival date is required")
	private Date trainArrivalDate;
	@NotNull(message = "Seat availability map is required")
	private Map<BookingClass, Integer> seatAvailability; // Map to store seat availability
	@NotNull(message = "Fares map is required")
	private Map<BookingClass, Integer> fares; // Map to store fare information
//	@Transient
//	private String depatureDate;
//	@Transient
//	private String arrivalDate;

}
