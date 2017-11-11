# sourdough-java
The java source code for the sourdough-java project during the November 2017 hackathon.

This project hosts a Web Push API which is compatible with both Chrome and Firefox implementations. At this time, we do not support Apple's Push Notification Service (APNS) provider.

REST services are provided using the Spring Framework. Objects are stored in an in-memory HSQLDB.

## Run the project

1. Run Jetty
    ```
    $ mvn jetty:run
    ```

## Generate the VAPID keypair (new environments)

The steps to generate a new VAPID keypair are:

1. Generate the private key in /src/test/resources

    ```
    $ openssl ecparam -name prime256v1 -genkey -noout -out vapid_private.pem
    ```

2. Generate the public key in /src/main/resources

    ```
    $ openssl ec -in vapid_private.pem -pubout -out vapid_public.pem
    ```

3. View the public/private key

    ```
    $ openssl ec -in vapid_private.pem -text -noout -conv_form uncompressed
    ```

4. Encode the keys as base64 using node:

    ```
    $ node
    > var publicKey = new Buffer([0x04,0xe1,0xfc,0x9d,0x34,0x00,0xe6,0x26,0x61,0x97,0x6d,0xfe,0x34,0x2c,0xc6,0x1b,0xda,0x6b,0xbc,0xe6,0x79,0x04,0x4d,0x0c,0x25,0x70,0x56,0xf8,0x65,0x24,0x40,0x8b,0xd1,0x55,0x35,0x41,0xdf,0x62,0x71,0x99,0x7d,0x15,0xd6,0x3e,0xb3,0xd2,0xbe,0xeb,0x9d,0x3e,0xfe,0x6e,0x08,0xba,0x7f,0x68,0x39,0x7c,0xc3,0xe9,0x02,0x1e,0x5b,0xae,0xa3]);
    > publicKey.toString('base64');
    'BOH8nTQA5iZhl23+NCzGG9prvOZ5BE0MJXBW+GUkQIvRVTVB32JxmX0V1j6z0r7rnT7+bgi6f2g5fMPpAh5brqM='
    
    > var privateKey = new Buffer([0x4d,0x19,0x58,0xff,0xbc,0x90,0xce,0xfa,0x9c,0x2e,0x98,0x07,0x41,0x3c,0x62,0x53,0x97,0xd5,0xcc,0x00,0x2f,0x03,0x0f,0xdc,0x75,0x28,0x79,0x90,0xb1,0x4b,0x36,0xa8]);
    > privateKey.toString('base64');
    'TRlY/7yQzvqcLpgHQTxiU5fVzAAvAw/cdSh5kLFLNqg='
    ```

5. Put the Base64 values into /src/main/resources/application.properties

## References

* https://github.com/web-push-libs/webpush-java
    