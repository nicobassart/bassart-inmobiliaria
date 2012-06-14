package inmobiliaria.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author  Armando J. Alaniz Aragon
 * @since   20090312
 * @version 1.0
 */
public final class DateUtil {
    public  static final int YEAR = 0;
    public static final int MONTH = 1;
    
    private static int getDateDiffInYears(Date startDate, Date endDate) {
        return getDateDiffInMonths(startDate, endDate)/12;
    }
    
    public static int getDateDiffInMonths(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        int startYear = -1, startMonth = -1;
        int endYear = -1, endMonth = -1;
        int months = 0;
        int factor = 1;
        
        if (startDate.after(endDate)) {
            factor = -1;
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        } else {
            startCal.setTime(startDate);
            endCal.setTime(endDate);
        }
        
        startYear = startCal.get(Calendar.YEAR);
        startMonth = startCal.get(Calendar.MONTH) + 1;
        endYear = endCal.get(Calendar.YEAR);
        endMonth = endCal.get(Calendar.MONTH) + 1;
        
        if (startYear == endYear) {
            months = endMonth - startMonth;
        } else {
            months = 12 - startMonth;
            months += endMonth;
            --endYear;
            if (endYear - startYear > 0) {
                months += (endYear - startYear)*12;
            }
        }
        months *= factor;
        return months;
    }
    
    public static int getDateDiff(int field, Date startDate, Date endDate) {
        if (field == YEAR) {
            return getDateDiffInYears(startDate, endDate);
        } else if (field == MONTH) {
            return getDateDiffInMonths(startDate, endDate);
        } else {
            return 0;
        }
    }

    public static int calcularMeses(String inicio, String fin) {
    	int diferencia = 0;
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy"); 
		try {
			java.util.Date inicid = sdf.parse(inicio);
			java.util.Date find = sdf.parse(fin);
			diferencia = DateUtil.getDateDiffInMonths(inicid, find);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diferencia;
	}
}