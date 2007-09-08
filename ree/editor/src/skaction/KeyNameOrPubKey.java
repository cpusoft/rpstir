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
public class KeyNameOrPubKey extends AsnChoice
    {
    public AsnOctetString keyName = new AsnOctetString();
    public TypeAndKey pub_key = new TypeAndKey();
    public KeyNameOrPubKey()
        {
        _tag = AsnStatic.ASN_CHOICE;
        _type = (short)AsnStatic.ASN_CHOICE;
        _setup((AsnObj)null, keyName, (short)0, (int)0x0);
        keyName._boundset(1, 16);
        _setup(keyName, pub_key, (short)0, (int)0x0);
        }
    public KeyNameOrPubKey set(KeyNameOrPubKey frobj)
        {
        ((AsnObj)this).set(frobj);
	return this;
	}
    }
