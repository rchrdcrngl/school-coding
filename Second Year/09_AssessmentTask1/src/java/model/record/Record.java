package model.record;

import java.sql.Date;

public class Record{
    int recordNO;
    int idNO;
    int dosageNo;
    Date dosageDate;
    String vaccineManufacturer;
    String dosageLotNo;
    String siteCity;
    String siteCountry;
    String firstName;
    String lastName;

    public Record(int recordNO, int idNO, int dosageNo, Date dosageDate, String vaccineManufacturer, String dosageLotNo, String siteCity, String siteCountry) {
        this.recordNO = recordNO;
        this.idNO = idNO;
        this.dosageNo = dosageNo;
        this.dosageDate = dosageDate;
        this.vaccineManufacturer = vaccineManufacturer;
        this.dosageLotNo = dosageLotNo;
        this.siteCity = siteCity;
        this.siteCountry = siteCountry;
    }
    
    public Record(int recordNO, int idNO, String firstName, String lastName, int dosageNo, Date dosageDate, String vaccineManufacturer, String dosageLotNo, String siteCity, String siteCountry) {
        this.recordNO = recordNO;
        this.idNO = idNO;
        this.dosageNo = dosageNo;
        this.dosageDate = dosageDate;
        this.vaccineManufacturer = vaccineManufacturer;
        this.dosageLotNo = dosageLotNo;
        this.siteCity = siteCity;
        this.siteCountry = siteCountry;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRecordNO() {
        return recordNO;
    }

    public int getIdNO() {
        return idNO;
    }

    public int getDosageNo() {
        return dosageNo;
    }

    public Date getDosageDate() {
        return dosageDate;
    }

    public String getVaccineManufacturer() {
        return vaccineManufacturer;
    }

    public String getDosageLotNo() {
        return dosageLotNo;
    }

    public String getSiteCity() {
        return siteCity;
    }

    public String getSiteCountry() {
        return siteCountry;
    }
    
    
}