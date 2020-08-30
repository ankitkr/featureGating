package com.showbooking.events;

public class Screen {
    Long id;
    Integer totalSeats;
    Boolean[][] seatingLayout;

    Screen(Long id) {
        this.id = id;
        this.totalSeats = 0;
        this.seatingLayout = null;
    }

    public void addLayout(Boolean[][] layout) {
        int seatCount = 0;
        this.seatingLayout = new Boolean[layout.length][];
        for (int i = 0; i < this.seatingLayout.length; ++i) {
            this.seatingLayout[i] = new Boolean[layout[i].length];
            System.arraycopy(layout[i], 0, this.seatingLayout[i], 0, this.seatingLayout[i].length);

            seatCount += this.seatingLayout[i].length;
        }
        this.totalSeats = seatCount;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getTotalSeats() {
        return this.totalSeats;
    }

    public Boolean[][] getSeatingLayout(){
        return this.seatingLayout;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", total seats: " + totalSeats;
    }
}
