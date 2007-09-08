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

// char sfcsid[] = "@(#)AsnIA5String.java 622E"
package asn;

import asn.*;

public class AsnIA5String extends asn.AsnString
{
    public AsnIA5String()
    {
        _tag = AsnStatic.ASN_IA5_STRING;
        _type = (short)AsnStatic.ASN_IA5_STRING;
    }

    public AsnIA5String(int tag)
    {
        _tag = tag;
        _type = (short)AsnStatic.ASN_IA5_STRING;
    }
}
