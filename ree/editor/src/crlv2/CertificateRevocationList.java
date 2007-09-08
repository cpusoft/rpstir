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
package crlv2;
import Algorithms.*;
import extensions.*;
// import serial_number.*;
import name.*;
import asn.*;
public class CertificateRevocationList extends AsnArray
    {
    public CertificateRevocationListToBeSigned toBeSigned = new CertificateRevocationListToBeSigned();
    public AlgorithmIdentifier algorithm = new AlgorithmIdentifier();
    public AsnBitString signature = new AsnBitString();
    public CertificateRevocationList()
        {
        _tag = AsnStatic.ASN_SEQUENCE;
        _type = (short)AsnStatic.ASN_SEQUENCE;
        _tag = AsnStatic.ASN_SEQUENCE;
        _type = (short)AsnStatic.ASN_SEQUENCE;
        _tag = AsnStatic.ASN_CHOICE;
        _type = (short)AsnStatic.ASN_CHOICE;
        _tag = AsnStatic.ASN_CHOICE;
        _type = (short)AsnStatic.ASN_CHOICE;
        _tag = AsnStatic.ASN_BITSTRING;
        _type = (short)AsnStatic.ASN_BITSTRING;
        _tag = AsnStatic.ASN_CHOICE;
        _type = (short)AsnStatic.ASN_CHOICE;
        _tag = AsnStatic.ASN_SEQUENCE;
        _type = (short)AsnStatic.ASN_SEQUENCE;
        _setup((AsnObj)null, toBeSigned, (short)0, (int)0x0);
        _setup(toBeSigned, algorithm, (short)0, (int)0x0);
        _setup(algorithm, signature, (short)0, (int)0x0);
        }
    public AsnObj _dup()
        {
        CertificateRevocationList objp = new CertificateRevocationList();
        _set_pointers(objp);
        return objp;
        }

    public CertificateRevocationList index(int index)
        {
        return (CertificateRevocationList)_index_op(index);
        }

    public CertificateRevocationList set(CertificateRevocationList frobj)
        {
        ((AsnObj)this).set(frobj);
	return this;
	}
    }
