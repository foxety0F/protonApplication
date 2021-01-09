package com.foxety0f.proton.modules.reporting.utils;

import java.util.Date;

public class ReportingColumnsTransform {

    public Object transformByType(String value, Integer type) {
	switch (type) {
	case 100:
	    return value.split(";");
	case 101 :
	    return value;
	case 102 :
	    return new Date(value);
	case 103 :
	    return Integer.parseInt(value);
	case 104 :
	    return Double.parseDouble(value);
	case 105 :
	    return (value.equals("true") || value.equals("t") || value.equals("yes") || value.equals("y") || value.equals("1")) ? true : false;
	case 106 :
	    return new Date(value);
	case 107 :
	    return value;
	case 108 : 
	    return value;
	case 109 : 
	    return value;
	default:
	    break;
	}
	
	return null;
    }
}
