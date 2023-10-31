#!/bin/bash
# What: Makes a truststore for running the application from maven. By grabing the tls cert from the
#       test website https://magenicautomation.azurewebsites.net for running the tests.
#       And the tls cert from the Artifactory repo, so that artifacts can be loaded and deployed to it.
#       Puts them into a nice Java trust store that can be used to run the tests.
set -x
FQDN="magenicautomation.azurewebsites.net"
PORT="443"
TS="truststore.jks"
PW="changeit"
echo "Grab the cert for the test web server that off which queries are bounced"
echo -n | openssl s_client -showcerts -connect $FQDN:$PORT | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > __tmp.cert
export PATH=/usr/lib/jvm/jre-1.8.0/bin:$PATH
rm -rf $TS
keytool -import -noprompt -storepass $PW -alias $FQDN -file __tmp.cert -keystore $TS

FQDN="jfrogbuild.deltads.ent"
echo "Grab the cert for the Artifactory repository use to load artifacts"
echo -n | openssl s_client -showcerts -connect $FQDN:$PORT | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > __tmp.cert
keytool -import -noprompt -storepass $PW -alias $FQDN -file __tmp.cert -keystore $TS

FQDN="sonarqube.ut.dentegra.lab"
echo "Grab the SQ cert"
echo -n | openssl s_client -showcerts -connect $FQDN:$PORT | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > __tmp.cert
keytool -import -noprompt -storepass $PW -alias $FQDN -file __tmp.cert -keystore $TS

keytool -list -v -storepass $PW -keystore $TS
echo "Please make sure that when you run maven you pass -Djavax.net.ssl.trustStore={workspace}/etc/$TS -Dnet.ssl.trustStorePassword=changeit"
rm __tmp.cert
