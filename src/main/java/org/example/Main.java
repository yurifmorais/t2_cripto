package org.example;

import java.math.BigInteger;
import java.util.Base64;

import org.apache.commons.codec.DecoderException;

//(use vetores char ou int): em java eu utilizei vetor de byte pq ai ja converte direto
public class Main{
    public static void main(String[] args) throws DecoderException {
        //parte 1
        String base64 = "QWNvcmRhUGVkcmluaG9RdWVob2pldGVtY2FtcGVvbmF0bw==";
        String hex = "41636f72646150656472696e686f517565686f6a6574656d63616d70656f6e61746f";
        base64ParaHex(base64);
        hexParaBase64(hex);

        //parte 2
        String plaintextHex = "41636f72646150656472696e686f517565686f6a6574656d63616d70656f6e61746f";
        String keyHex = "0b021e0701003e0a0d060c0807063d1a0b0f0e060a1a020c0f0e03170403010f130e";
        xorCipher(plaintextHex, keyHex);

        //parte 3
        String ciphertextHex = "4a61717565616e6f697465666f696c6f6e67616c6f6e67616c6f6e67616c6f6e6761";
        xorDecipher(ciphertextHex, keyHex);
    }

    public static void hexParaBase64(String hex) {
        //hex -> byte[] decodedBytes -> base64
        byte[] decodedBytes = new BigInteger(hex, 16).toByteArray();
        String base64 = Base64.getEncoder().encodeToString(decodedBytes);
        System.out.printf("\nhexParaBase64: %s", base64);
    }
    public static void base64ParaHex(String base64) {
        //base64 -> byte[] decodedBytes -> hex
        byte[] decodedBytes = Base64.getDecoder().decode(base64); //byte[] e equivalente a um vetor de char sem sinal
        String hex = String.format("%040x", new BigInteger(1, decodedBytes));
        System.out.printf("\nbase64ParaHex: %s", hex);
    }

    //parte 2
    public static void xorCipher(String plaintextHex, String keyHex) {
        // Converte os valores em hexadecimal para vetores de bytes
        byte[] plaintext = new BigInteger(plaintextHex, 16).toByteArray();
        byte[] key = new BigInteger(keyHex, 16).toByteArray();

        // Verifica se os vetores têm o mesmo tamanho
        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("Os vetores devem ter o mesmo tamanho");
        }

        // Cria um novo vetor para armazenar o resultado da cifra
        byte[] ciphertext = new byte[plaintext.length];

        // Realiza a cifra XOR bit-a-bit entre o plaintext e a chave
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]);
        }

        // Converte o resultado da cifra para hexadecimal
        String ciphertextHex = String.format("%040x", new BigInteger(1, ciphertext));

        // Imprime o resultado da cifra em hexadecimal
        System.out.printf("\nXOR Cipher: %s", ciphertextHex);
    }

    //parte 3
    public static void xorDecipher(String ciphertextHex, String keyHex) {
        // Converte os valores em hexadecimal para vetores de bytes
        byte[] ciphertext = new BigInteger(ciphertextHex, 16).toByteArray();
        byte[] key = new BigInteger(keyHex, 16).toByteArray();

        // Verifica se os vetores têm o mesmo tamanho
        if (ciphertext.length != key.length) {
            throw new IllegalArgumentException("Os vetores devem ter o mesmo tamanho");
        }

        // Cria um novo vetor para armazenar o resultado da decifra
        byte[] plaintext = new byte[ciphertext.length];

        // Realiza a decifra XOR bit-a-bit entre o ciphertext e a chave
        for (int i = 0; i < ciphertext.length; i++) {
            plaintext[i] = (byte) (ciphertext[i] ^ key[i]);
        }

        // Converte o resultado da decifra para hexadecimal
        String plaintextHex = String.format("%040x", new BigInteger(1, plaintext));

        // Imprime o resultado da decifra em hexadecimal
        System.out.printf("\nXOR Decipher: %s", plaintextHex);
    }
}