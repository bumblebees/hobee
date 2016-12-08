package bumblebees.hobee.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class EventDetails {

    EventDetails(){
        super();
    }

    private String event_name;
    private String host_id;
    private String host_name;
    private int age_min;
    private int age_max;
    private String gender;
    private String timestamp;
    private int maximum_people;
    private String location;
    private String description;
    private List<PublicUser> users_pending;
    private List<PublicUser> users_accepted;

    private Hobby hobby;

    public EventDetails(String event_name, String host_id, String host_name, int age_min, int age_max,
                        String gender, String timestamp, int maximum_people, String location, String description,
                        List<PublicUser> users_pending, List<PublicUser> users_accepted, Hobby hobby) {
        this.event_name = event_name;
        this.host_id = host_id;
        this.host_name = host_name;
        this.age_min = age_min;
        this.age_max = age_max;
        this.gender = gender;
        this.timestamp = timestamp;
        this.maximum_people = maximum_people;
        this.location = location;
        this.description = description;
        this.users_pending = users_pending;
        this.users_accepted = users_accepted;
        this.hobby = hobby;
    }

    public String toString(){
        return "Event name: " + event_name + " Host ID " + host_id + " Host name: " + host_name +
                "\nAge Range " + age_min + "-" + age_max + " Gender " + gender + " Timestamp " +
                timestamp + "\n Ammount of People " + maximum_people + " Location + " + location +
                "\n Description " + description + " Users pending: " + users_pending + " Users accepted: +" +
                users_accepted;
    }

    public void confirmUser(PublicUser user) {
        if (users_pending.contains(user)) {
            users_accepted.add(user);
            users_pending.remove(user);
        }
    }

    /**
     * Add a user to the list of pending users of the event.
     * @param user - user to be added
     */
    public void addUser(PublicUser user){
        users_pending.add(user);
    }

    /**
     * Check if the User exists in the list of accepted or pending users.
     * @param user - user to be checked
     * @return true if the user exists, false otherwise
     */
    public boolean checkUser(PublicUser user){
         if(users_pending.contains(user)){
            return true;
        }
        if(users_accepted.contains(user)){
            return true;
        }
        return false;
    }

    /**
     * Reject a user from joining an event and remove the user from the pending user list.
     * @param user
     */
    public void rejectUser(PublicUser user){
        users_pending.remove(user);
    }

    /**
     * Get the day of the week when the event takes place.
     * @return int representing the day of the week starting from 1 (Sunday)
     */
    public int getDayOfTheWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(this.timestamp)*1000L);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Retrieve a formatted string representing the date and time when the event takes place.
     * @return
     */
    public String getDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(this.timestamp)*1000L);
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(sdfDateTime.format(cal.getTime()));
    }

    public String getTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(this.timestamp)*1000L);
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("HH:mm");
        return String.valueOf(sdfDateTime.format(cal.getTime()));
    }

    public List<PublicUser> getUsers_pending(){
        return users_pending;
    }

    public List<PublicUser> getUsers_accepted(){
        return users_accepted;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getHost_id() {return host_id; }

    public String getHost_name() {
        return host_name;
    }

    public int getAge_min() {
        return age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public String getGender() {
        return gender;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getMaximum_people() {
        return maximum_people;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getHobbyName(){
        return hobby.getName();
    }

}

