package com.gaurav.cis.demo.submission;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SubmissionSequenceGenerator implements IdentifierGenerator {
    private Date date = new Date();
    private DateFormat df = new SimpleDateFormat("yyyyMMdd");

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "submission";
        String dateStr = df.format(date);
        String uniqueID = String.valueOf(new Random().nextInt(900000) + 100000); // Generating a random 6-digit number
        return prefix + "" + dateStr + "" + uniqueID;
    }
}
