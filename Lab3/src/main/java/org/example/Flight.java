    package org.example;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;

    import java.time.LocalTime;
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    public class Flight{
        private Aircraft aircraft;
        private int idNumber;
        private LocalTime landingTime;
        private LocalTime parkingTime;

        public Flight()
        {
            aircraft=null;
            idNumber=0;
            landingTime=null;
            parkingTime=null;
        }
        public boolean intersectFlight(Flight flight){
            return !(flight.parkingTime.isBefore(this.landingTime) || flight.landingTime.isAfter(this.parkingTime));
        }
    }
