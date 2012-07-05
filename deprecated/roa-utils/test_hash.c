#include "roa_utils.h"
#include "cryptlib.h"


/*
 * $Id$ 
 */

int main(
    int argc,
    char **argv)
{
    CRYPT_CONTEXT hashContext;
    uchar hash[40];
    uchar *signature;
    int ansr = 0,
        lth;
    struct Certificate cert;
    struct RSAPubKey pubkey;

    if (argc < 2)
    {
        (void)fprintf(stderr, "Usage: test_hash filename\n");
        return (1);
    }
    Certificate(&cert, 0);
    RSAPubKey(&pubkey, 0);
    // get public key from certificate in argv[1]
    get_casn_file(&cert.self, argv[1], 0);
    signature =
        (uchar *) calloc(1,
                         size_casn(&cert.toBeSigned.
                                   subjectPublicKeyInfo.subjectPublicKey));
    read_casn(&cert.toBeSigned.subjectPublicKeyInfo.subjectPublicKey,
              signature);
    // extract it
    lth = decode_casn(&pubkey.self, &signature[1]);
    lth = encode_casn(&pubkey.self, signature);
    memset(hash, 0, 40);
    cryptInit();
    // hash it
    ansr = cryptCreateContext(&hashContext, CRYPT_UNUSED, CRYPT_ALGO_SHA);
    ansr = cryptEncrypt(hashContext, signature, lth);
    ansr = cryptEncrypt(hashContext, signature, 0);
    ansr = cryptGetAttributeString(hashContext, CRYPT_CTXINFO_HASHVALUE, hash,
                                   &lth);
    lth = read_casn(&pubkey.self, signature);
    cryptDestroyContext(hashContext);
    ansr = cryptCreateContext(&hashContext, CRYPT_UNUSED, CRYPT_ALGO_SHA);
    ansr = cryptEncrypt(hashContext, signature, lth);
    ansr = cryptEncrypt(hashContext, signature, 0);
    memset(hash, 0, 40);
    ansr = cryptGetAttributeString(hashContext, CRYPT_CTXINFO_HASHVALUE, hash,
                                   &lth);

    cryptDestroyContext(hashContext);
    cryptEnd();
    return 1;
}