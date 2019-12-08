package model.dao.impl;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

import model.dao.DaoFactory;
import model.dao.DiagnosisDao;
import model.dao.NoteDao;
import model.dao.PatientDao;
import model.dao.UserDao;



public class JDBCDaoFactory extends DaoFactory{
	
	@Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
	
	@Override
    public PatientDao createPatientDao() {
        return new JDBCPatientDao(getConnection());
    }
	
	@Override
    public DiagnosisDao createDiagnosisDao() {
        return new JDBCDiagnosisDao(getConnection());
    }
	
	@Override
    public NoteDao createNoteDao() {
        return new JDBCNoteDao(getConnection());
    }
	
    public Connection getConnection(){
        try {
        	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true"
            		+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root" ,
                    "root" );
            
        } catch (SQLException e) {throw new RuntimeException(e);} 
    }
}