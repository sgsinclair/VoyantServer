/* 
 *  Copyright (C) 2012 AW2.0 Ltd
 *
 *  org.aw20 is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  Free Software Foundation,version 3.
 *  
 *  OpenBD is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with org.aw20.  If not, see http://www.gnu.org/licenses/
 *  
 *  Additional permission under GNU GPL version 3 section 7
 *  
 *  If you modify this Program, or any covered work, by linking or combining 
 *  it with any of the JARS listed in the README.txt (or a modified version of 
 *  (that library), containing parts covered by the terms of that JAR, the 
 *  licensors of this Program grant you additional permission to convey the 
 *  resulting work. 
 *  
 *  $Id: FileUtil.java 2981 2012-08-08 21:01:27Z alan $
 */
package org.aw20.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends Object {
	
	public static long SECS_MS   = 1000;
  public static long MINS_MS   = 60 * SECS_MS;
  public static long HOUR_MS   = 60 * MINS_MS;
  public static long DAY_MS    = 24 * HOUR_MS;
  public static long WEEK_MS   = 7 * DAY_MS;
  public static long MONTH_MS  = 31 * DAY_MS;
  public static long YEAR_MS   = 52 * WEEK_MS;

	public static java.util.Date parseHttpDate( String _dateStr ){
		if ( _dateStr == null ) return null;
		
		int c1 = _dateStr.lastIndexOf(" ");
		
		if ( c1 != -1 )
			_dateStr = _dateStr.substring( 0, c1 ).trim();
		
		return parseDate( _dateStr,  "EEE, d MMM yyyy HH:mm:ss" );
	}
	
	public static String getCookieDate( long l ){
		return getDateString( l, "EEE, dd-MMM-yyyy HH:mm:ss" ) + " GMT";
	}
	
	public static String getCookieDate( Date _date ){
		return getDateString( _date, "EEE, dd-MMM-yyyy HH:mm:ss" ) + " GMT";
	}
	
	public static String getHttpDate( long l ){
		return getDateString( l, "EEE, d MMM yyyy HH:mm:ss" ) + " GMT";
	}
		
	public static String getHttpDate( java.util.Date _date ){
		return getDateString( _date, "EEE, d MMM yyyy HH:mm:ss" ) + " GMT";
	}
	
	public static String getDateString( java.util.Date _date, String _pattern ){
		return new SimpleDateFormat( _pattern ).format(_date);
	}
	
	public static String getDateString( long _epoch, String _pattern ){
		return new SimpleDateFormat( _pattern ).format(_epoch);
	}

	public static String getJSONDate( long l ){
		return getDateString( l, "yyyy-MM-dd'T'HH:mm:ss" );
	}
	
	public static String getJSONDate( java.util.Date _date ){
		return getDateString( _date, "yyyy-MM-dd'T'HH:mm:ss" );
	}

	public static String getSQLDate( java.util.Date _date  ){
		return getDateString( _date, "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static java.util.Date parseJSONDate( String _dateStr ){
		return parseDate( _dateStr,  "yyyy-MM-dd'T'HH:mm:ss" );
	}
	
	public static java.util.Date parseDate( String sDate, String _datePattern ){
		sDate = (sDate != null) ? sDate.trim() : null;
		
		/* if the date is null or empty, no point in going forward */
		if ( sDate == null || sDate.length() == 0 )
			return null;
		
		ParsePosition pp;
		java.util.Date d = null;
		DateFormat df = new SimpleDateFormat(_datePattern);
		df.setLenient(false);
		
		try {
			pp = new ParsePosition(0);
			d = df.parse(sDate,pp);
			if ( pp.getIndex()!=sDate.length() ) {
				d = null;
			}
		}catch (Exception ex1) {
			return null;
		}
    return d;
	}

	
  public static String formatAge( long age ){
    if ( age < 0 ) age = age * -1;
    
    StringBuffer  ageString = new StringBuffer( 32 );

    //-- Years
    long years  = age / YEAR_MS;
    if ( years > 0 ){
      ageString.append( years + " year" );
      if ( years > 1 ) ageString.append( "s" );
      
      ageString.append( " " );
      age = age - ( years * YEAR_MS );
    }
    
    //-- Months
    long months = age / MONTH_MS;
    if ( months > 0 ){
      ageString.append( months + " month" );
      if ( months > 1 ) ageString.append( "s" );
      
      ageString.append( " " );
      age = age - ( months * MONTH_MS );
    }
    
    //-- Days
    long days = age / DAY_MS;
    if ( days > 0 ){
      ageString.append( days + " day" );
      if ( days > 1 ) ageString.append( "s" );
      
      ageString.append( " " );
      age = age - ( days * DAY_MS );
    }
    
    //-- Hours
    long hours  = age / HOUR_MS;
    if ( hours > 0 ){
      ageString.append( hours + " hour" );
      if ( hours > 1 ) ageString.append( "s" );
      
      ageString.append( " " );
      age = age - ( hours * HOUR_MS );
    }
    
    //-- Minutes
    long mins = age / MINS_MS;
    if ( mins > 0 ){
      ageString.append( mins + " min" );
      if ( mins > 1 ) ageString.append( "s" );
      
      ageString.append( " " );
      age = age - ( mins * MINS_MS );
    }
    
    //-- Seconds
    long seconds  = age / SECS_MS;
    if ( seconds > 0 ){
      ageString.append( seconds + " sec" );
      if ( seconds > 1 ) ageString.append( "s" );
      ageString.append( " " );
    }
    
    return ageString.toString().trim();
  }
  
  public static Date getFromJavaScriptDate( String jsDateStr ){
  	return DateUtil.parseDate(jsDateStr, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  }
  
}
