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
package skaction;
import name.*;
import Algorithms.*;
import certificate.*;
import crlv2.*;
import asn.*;
public class ErrorResp extends AsnSequence
    {
    public AsnInteger opcode = new AsnInteger();
    public ErrorTableInErrorResp error = new ErrorTableInErrorResp();
    public ErrorTableDefined details = new ErrorTableDefined();
    public ErrorResp()
        {
        _tag = AsnStatic.ASN_SEQUENCE;
        _type = (short)AsnStatic.ASN_SEQUENCE;
        _setup((AsnObj)null, opcode, (short)0, (int)0x0);
        _setup(opcode, error, (short)0, (int)0x0);
        _setup(error, details, (short)(AsnStatic.ASN_OPTIONAL_FLAG), (int)0x0);
        }
    public ErrorResp set(ErrorResp frobj)
        {
        ((AsnObj)this).set(frobj);
	return this;
	}
    }
