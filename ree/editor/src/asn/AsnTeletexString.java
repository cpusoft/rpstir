/* ***** BEGIN LICENSE BLOCK *****
 * 
 * BBN Rule Editor/Engine for Address and AS Number PKI
 * Verison 1.0
 * 
 * COMMERCIAL COMPUTER SOFTWARE�RESTRICTED RIGHTS (JUNE 1987)
 * US government users are permitted restricted rights as
 * defined in the FAR.  
 *
 * This software is distributed on an "AS IS" basis, WITHOUT
 * WARRANTY OF ANY KIND, either express or implied.
 *
 * Copyright (C) BBN Technologies 2007.  All Rights Reserved.
 *
 * Contributor(s):  Charlie Gardiner
 *
 * ***** END LICENSE BLOCK ***** */

// char sfcsid[] = "@(#)AsnTeletexString.java 622E"
package asn;

import asn.*;

public class AsnTeletexString extends AsnString
{
    public AsnTeletexString()
    {
        _tag = AsnStatic.ASN_T61_STRING;
        _type = (short)AsnStatic.ASN_T61_STRING;
    }

    public void set(AsnByteArray value)
    {
        write(value, (value.getLength()));
    }

}
