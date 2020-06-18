
package countDays;

//program to count dates left 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;


public class CountdownDays {
    
    // returns current date in  "dd/mm/yyyy"
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);
    }
    
    //main
    public static void main(String[] args) {
    	displayCountdown(args[0]); //commandline arg with format dd/mm/yyyy
    }
    
    
    

    
 //getSubstring:
       //get substring of String s with index position from i to j 
     
   
    
    public static String getSubstring(String s, int i, int j)	{
    	//set empty substring 
    	String Substring = "";
    	//throw exception
    	if (j < i)	{
    		throw new IllegalArgumentException("the first input should be smaller than the second input");
    	}
    	//find the character between position i and j
    	for (int a = i; a <= j; a++  )	{	
    		Substring = Substring + s.charAt(a);	
    	}

    	//return 
    	return Substring;
    	}
    

 //getDay 
     
    
    public static int getDay(String date)	{
    	//get days (position 0 to 1) by calling getSubstring method
    	String dayNumber = getSubstring(date,0,1);
    	//change string into int
       int day = Integer.parseInt(dayNumber);
        return day;
    }
    
 //getMonth 
    public static int getMonth(String date)	{
    	//get months (position 3 to 4) by calling getSubstring method
    	String monthNumber = getSubstring(date,3,4);
    	//change string into int
       int month = Integer.parseInt(monthNumber);
        return month;   
    }
    
 //getYear 
    public static int getYear(String date)	{
    	//get year (position 6 to 9) by calling getSubstring method
    	String yearNumber = getSubstring(date,6,9);
    	//change string into int
       int year = Integer.parseInt(yearNumber);
        return year;
    }
    
    
 //isLeapYear
    //returns true if leap year
    
    public static boolean isLeapYear(int year)	{
    	//condition if the input year is century years
    	//need to be divisible by 400 to be a leap year
    	if (year % 100 == 0)	{
    		if (year % 400 == 0) 	{
    			return true;
    		}else {
    			return false;
    		}
    	}
    	
    	//condition if input year is not century years
    	//need to be divisible by 4 to be a leap year
    	else if(year % 4 == 0)	{
    		return true;
    	}
    	return false;
    }
    
 

 //getDaysInAMonth 
    //return number of days in a given month of given year
    
    public static int getDaysInAMonth(int month, int year)	{
    	//return days of 31 if input is month 1,3,5,7,8,10,12
    	if ((month == 1)||(month == 3)||(month == 5)||(month == 7)||(month == 8)||(month == 10)||(month == 12))	{
    		return 31;
    	//return days of 30 if input is month 4,6,9,11
    	}else if ((month == 4)||(month == 6)||(month == 9)||(month == 11))	{
    		return 30;
    	//condition if input month is February
    	}else {
    		//return 29 days if its leap year
    		if (isLeapYear(year) == true)	{
    			return 29;
    		//return 28 days otherwise
    		}else {
    			return 28;
    		}
    	}
    }
    
    

//dueDateHasPassed
    public static boolean dueDateHasPassed(String c, String d)	{
    	//if due year < current year, return true
    	if (getYear(d) < getYear(c))	{
    		return true;
    	//if due year == current year, check conditions for due months
    	}else if (getYear(d) == getYear(c))	{
    		//if due month < current month, return true
    		if (getMonth(d) < getMonth(c))	{
    			return true;
    		//if due month == current month, check conditions for due date
    		}else if(getMonth(d) == getMonth(c))	{
    			//if due day <= current day, return true
    			if (getDay(d) <= getDay(c))	{
    				return true;
    			//if due day > current day, return false
    			}else {
    				return false;
    			}
    			
    		}
    		//if due month > current month, return false
    		else {
    			return false;
    		}
    	}
    	//if due year > current year, return false
    	else {
    		return false;
    	}
    }
    
   
    
 //countDaysLeft 
    
    public static int countDaysLeft(String c, String d)	{
		int days = 0;


    	//return 0 is due date has passed
    	if (dueDateHasPassed(c,d) == true)	{
    		return 0;
    	}else {
    	//condition if due Year (d) > current Year (c)
    	if (getYear(c) < getYear(d))	{
    		//get the difference in years between c and d
    		int difference = getYear(d) - getYear(c);
    		//if the difference is more than or equal to 1
    		//reduce it to the last date of the current year
    		if (difference >= 1)	{		
    			//first, reduce the dueDate to the first day of due month 
    			days += getDay(d)-1;
    		    //then reduce the dueDate to the first day of the due year
    			for (int i = (getMonth(d)-1); i >= 1; i--) {
    			    days += getDaysInAMonth(i,getYear(d));
    			}        		

    			//reduce the dueDate to the first day of (current year + 1)
    			for (int i = getYear(c)+1; i < getYear(d);i++)	{
    				if(isLeapYear(i) == true)	{
    					days += 366;
    				}else {
    					days += 365;
    				}

    			}
    			//reduce the dueDate to the 1st day of (current month + 1)
    				for (int i = (getMonth(c)+1); i <= 12; i++)	{
    					days += getDaysInAMonth(i,getYear(c));
    				}
    			//reduece the dueDate to the currentDate
    				int cDay = getDay(getCurrentDate());
    				int cMonth = getMonth(getCurrentDate());
    				int cYear = getYear(getCurrentDate());
    			
    				days += (getDaysInAMonth(cMonth,cYear) - cDay + 1);
    		}
    		
    	}
    	//condition if the dueYear == currentYear
    	if(getYear(c) == getYear(d))	{
    		//if dueMonth > currentMonth, reduce it to the 1st Day of dueMonth
    		if (getMonth(d) > getMonth(c))	{
    			days += getDay(d)-1;

    			//reduce it to the first day of (current month + 1)
    			for(int i = getMonth(d)-1; i > getMonth(c); i--)	{
    				days += getDaysInAMonth(i,getYear(c));

    			}
    			//reduce it to the currentDate
    			int cYear = getYear(getCurrentDate());
    			int cMonth = getMonth(getCurrentDate());
    			int cDay = getDay(getCurrentDate());
				days += (getDaysInAMonth(cMonth,cYear) - cDay +1);
			//if dueMonth == currentMonth, subtract dueDay by currentDay
    		}else if(getMonth(c) == getMonth(d))	{
    			days += (getDay(d)-getDay(getCurrentDate()));
    		}
    	}
    	}
    	return days;
    }

 //displayCountdown 
    
    public static void displayCountdown(String d)	{
    	String dueDate = d;
    	System.out.println("Today is: " + getCurrentDate());
    	System.out.println("Due date: " + dueDate);
    	if (dueDateHasPassed(getCurrentDate(),dueDate) == true)	{
    		System.out.println("The due date has passed! :( Better luck next time!");
    	}else {
    		System.out.println("You have " + countDaysLeft(getCurrentDate(),dueDate) + " days left. You can do it!");
    	}
    }
    
    
    
    
    
	}
    
    

