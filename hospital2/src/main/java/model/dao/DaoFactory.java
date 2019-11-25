package model.dao;

import model.dao.impl.JDBCDaoFactory;


public abstract class DaoFactory {
	
	private static DaoFactory daoFactory;
	
	public abstract UserDao createUserDao();	
	public abstract PatientDao createPatientDao();
	public abstract DiagnosisDao createDiagnosisDao();
	public abstract NoteDao createNoteDao();
	
    public static DaoFactory getInstance(){
        if( daoFactory == null ) {
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }	
}
