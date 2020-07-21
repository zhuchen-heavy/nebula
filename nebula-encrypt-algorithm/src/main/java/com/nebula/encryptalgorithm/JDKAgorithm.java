package com.nebula.encryptalgorithm;

import java.security.Provider;
import java.security.Security;

/**
 * <p>
 * jdk security中带的加密算法
 * </p>
 * @author: zhu.chen
 * @date: 2019-05-07
 */
public class JDKAgorithm {

    public static void main(String[] args) {
        System.out.println("-------列出加密服务提供者-----");
        /**
         * Provider:SUN - version:1.8
         * SUN (DSA key/parameter generation; DSA signing; SHA-1, MD5 digests; SecureRandom; X.509 certificates; JKS & DKS keystores; PKIX CertPathValidator; PKIX CertPathBuilder; LDAP, Collection CertStores, JavaPolicy Policy; JavaLoginConfig Configuration)
         *
         * Provider:SunRsaSign - version:1.8
         * Sun RSA signature provider
         *
         * Provider:SunEC - version:1.8
         * Sun Elliptic Curve provider (EC, ECDSA, ECDH)
         *
         * Provider:SunJSSE - version:1.8
         * Sun JSSE provider(PKCS12, SunX509/PKIX key/trust factories, SSLv3/TLSv1/TLSv1.1/TLSv1.2)
         *
         * Provider:SunJCE - version:1.8
         * SunJCE Provider (implements RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC)
         *
         * Provider:SunJGSS - version:1.8
         * Sun (Kerberos v5, SPNEGO)
         *
         * Provider:SunSASL - version:1.8
         * Sun SASL provider(implements client mechanisms for: DIGEST-MD5, GSSAPI, EXTERNAL, PLAIN, CRAM-MD5, NTLM; server mechanisms for: DIGEST-MD5, GSSAPI, CRAM-MD5, NTLM)
         *
         * Provider:XMLDSig - version:1.8
         * XMLDSig (DOM XMLSignatureFactory; DOM KeyInfoFactory; C14N 1.0, C14N 1.1, Exclusive C14N, Base64, Enveloped, XPath, XPath2, XSLT TransformServices)
         *
         * Provider:SunPCSC - version:1.8
         * Sun PC/SC provider
         *
         * Provider:Apple - version:1.8
         * Apple Provider
         */
        Provider[] pro = Security.getProviders();
        for (Provider p : pro) {
            System.out.println("Provider:" + p.getName() + " - version:" + p.getVersion());
            System.out.println(p.getInfo());
        }
        /**
         * MD2 / MD5
         * SHA / SHA-224 / SHA-256 / SHA-384 / SHA-512
         */
        System.out.println("-------列出系统支持的消息摘要算法-------");
        for (String s : Security.getAlgorithms("MessageDigest")) {
            System.out.println(s);
        }
        /**
         * RSA / DSA / DIFFIEHELLMAN / EC
         */
        System.out.println("-------列出系统支持的生成公钥和私钥对的算法-------");
        for (String s : Security.getAlgorithms("KeyPairGenerator")) {
            System.out.println(s);
        }
    }

}
