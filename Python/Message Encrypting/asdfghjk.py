import hashlib
from Crypto.Cipher import AES
from Crypto.Util import Counter
from base64 import b64encode
import os


class AESCipher(object):
    def __init__(self, key):
        self.block_size = AES.block_size
        self.key = hashlib.sha256(key.encode()).digest()  # Hash the key to 256 bits

    def encrypt(self, plain_text):
        plain_text = self.__pad(plain_text).encode('utf-8')  # Ensure plain_text is bytes
        # Create a 128-bit counter for AES
        counter = Counter.new(128)
        cipher = AES.new(self.key, AES.MODE_CTR, counter=counter)
        encrypted_text = cipher.encrypt(plain_text)
        return b64encode(encrypted_text).decode("utf-8")  # Encode result to Base64 for readability

    def __pad(self, plain_text):
        # Add padding to the plain text
        number_of_bytes_to_pad = self.block_size - len(plain_text) % self.block_size
        ascii_string = chr(number_of_bytes_to_pad)
        padding_str = number_of_bytes_to_pad * ascii_string
        padded_plain_text = plain_text + padding_str
        return padded_plain_text


if __name__ == '__main__':
    # Prompt for key and message
    key = input("Enter encryption key: ")
    msg = input("Enter message: ")

    cipher = AESCipher(key)
    # Repeat the message 10 times to test larger sizes
    msg = msg * 10

    print("Encrypted Message:")
    print(cipher.encrypt(msg))
