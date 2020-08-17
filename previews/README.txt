password 20200101

gpg --gen-key

Real name: barryme2049
Email address: barryme2049@gmail.com
You selected this USER-ID:
    "barryme2049 <barryme2049@gmail.com>"

pub   rsa2048 2020-08-05 [SC] [expires: 2022-08-05]
      0F0FB0F372A54E91A79B9023334F1FE061F5722D
uid                      barryme2049 <barryme2049@gmail.com>
sub   rsa2048 2020-08-05 [E] [expires: 2022-08-05]


Administrator@DESKTOP-9KHML0F MINGW64 ~/Desktop
$ gpg --gen-revoke 334F1FE061F5722D

sec  rsa2048/334F1FE061F5722D 2020-08-05 barryme2049 <barryme2049@gmail.com>

Create a revocation certificate for this key? (y/N) y
Please select the reason for the revocation:
  0 = No reason specified
  1 = Key has been compromised
  2 = Key is superseded
  3 = Key is no longer used
  Q = Cancel
(Probably you want to select 1 here)
Your decision? 3
Enter an optional description; end it with an empty line:
> barryme2049-Key is no longer used
>
Reason for revocation: Key is no longer used
barryme2049-Key is no longer used
Is this okay? (y/N) y
ASCII armored output forced.
-----BEGIN PGP PUBLIC KEY BLOCK-----
Comment: This is a revocation certificate

iQFXBCABCABBFiEEDw+w83KlTpGnm5AjM08f4GH1ci0FAl8qgAsjHQNiYXJyeW1l
MjA0OS1LZXkgaXMgbm8gbG9uZ2VyIHVzZWQACgkQM08f4GH1ci2nCwf/Sf3tIZQ8
NcmJcOweF9vUOOha8qhiiRUM1MUkkYGIVnsHulOr9dn0qPAA2HJahY5uCNhJz5EQ
eYzXi7oDokE45T9JqBJ8Oz0nf6xlPCPNQOBKuzLLnPXoulwbXrBzcf4jP1vy2BCV
3wqzCf0J+RcR6HZXWaM5uNbZJt8YEm7NKcTuFk3tuXyJVZWyjn8463PaNrl86zo4
u7upMxbwNmg00jZIfmF2Br2cQ9Z9/cu56ifetVz3ZnZzzpLrTkgUv2dC//MSk1Ny
TdYZJMcDdlom24QWdHeDFIiTcBU4fKBupi4nF47J8g/QnhsnaZJOVTVDgAlNSQ8R
uFK5CUIJMiL9uA==
=9VWs
-----END PGP PUBLIC KEY BLOCK-----
Revocation certificate created.

Please move it to a medium which you can hide away; if Mallory gets
access to this certificate he can use it to make your key unusable.
It is smart to print this certificate and store it away, just in case
your media become unreadable.  But have some caution:  The print system of
your machine might store the data and make it available to others!


$ gpg --keyserver hkp://hkps.pool.sks-keyservers.net --send-key 334F1FE061F5722D
gpg: sending key 334F1FE061F5722D to hkp://hkps.pool.sks-keyservers.net


$ gpg --export-secret-key 334F1FE061F5722D > secring.gpg
（git-bash 目录） 

或者 $ gpg --export-secret-keys -o secring.gpg

$ gpg --list-keys --keyid-format short