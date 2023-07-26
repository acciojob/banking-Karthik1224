package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag = validate(tradeLicenseId);
        if(flag == false)
        {
            String rearranged = reorganise(tradeLicenseId);
            if(rearranged.isEmpty())
            {
                throw new Exception("Valid License can not be generated");
            }
            else
            {
                this.tradeLicenseId = rearranged;
            }
        }

    }

    public boolean validate (String s)
    {
        for(int i=0; i<s.length()-1; i++)
        {
            if(s.charAt(i)==s.charAt(i+1))
            {
               return false;
            }
        }
        return true;
    }

    public String reorganise(String s)
    {
        int[]fre = new int[26];
        for(char ch : s.toCharArray())
        {
            fre[ch-'A']++;
        }
        int max = 0, letter=0;
        for(int i=0; i<26; i++)
        {
            if(fre[i]>max)
            {
                max = fre[i];
                letter=i;
            }
        }
        int n = s.length();
        if(max > (n+1)/2) return "";
        char[]ans = new char[n];
        int ind=0;
        for(int i=0; i<max; i++)
        {
            ans[ind] = (char)(letter+'A');
            ind = ind+2;
            fre[letter]--;
        }

        for(int i=0; i<26; i++)
        {
            while(fre[i]>0)
            {
                if(ind >= n) ind=1;
                ans[ind] = (char)(i+'A');
                ind += 2;
                fre[i]--;
            }
        }
        return String.valueOf(ans);
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
