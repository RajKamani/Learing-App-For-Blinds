package com.TheVision.tv;

public class Question1 {

    public String questions[] = {
            "Write down a pair of integers in which: sum is -6",
            "Write down a pair of integers in which: difference is -8",
            "Write down a pair of integers in which: sum is 1",
            "Write a pair of negative integers whose difference gives 7",
            "Write a negative integer and a positive integer whose sum is -6",
            "Write a negative integer and a positive integer whose difference is -7",
            "A quiz was held in which team A scored -35, 12, 0 and team B scored 12, 8, -30 in three successive rounds. Who won the quiz by scoring more marks? Can the integers be added in any manner?",
            "(-4) + (-6) =  using [Commutative property]",
            "-34 + 0 = ",
            "18 + (-18) =",
            "Calculate the mean of the first five whole numbers",
            "A cricketer scores the following runs in eight innings: 58, 56, 89, 69, 76, 78, 44, 90. Calculate the mean score.",
            "The height of 10 boys (in cm) was as follows:130, 150, 145, 165, 150, 143, 146, 123, 128, 136 Find the height of the shortest boy",
            "a + 4 =  and  a = – 4",
            "a + 5 = ,and  a = 0",
            "a + 4 = ,and a = 4",
            "a – 8 = ,and a = 8",
            "a – 8 = ,and a = -9",
            "5a = ,and a = 5",
            "x/3 = ,and  x = – 9",
            "5m + 1 = 11 so m=",
            "3p – 15 = 6 so p=",
            "The sum of numbers p and 5 is 10.",
            "6 subtracted from q is 9",
            "4k = 20 then k="
    };

    public String choices[][] = {
            {"-5 + (-1)","-6 + 5","5+(-5)","1+5"},
            {"5 + (-4)", " -5 + (-1)", "-9 – (-1) =", " nothing"},
            {"-6 + 5", "-9 + 8", "-5 + (-1) = -6", "5 + (-4)"},
            {"-2 - 9", "(-2) – (-9)", "2 - 9", "5-(-3)"},
            {"(-7) + 1", "(7) + 1", "5 + 1", "4 + 2"},
            { "(-9) + 2","(-4) – 3","(4) + 3", "(-4) + 3"},
            {"Team A", "Team B", "BOTH TEAM SAME SCORE", "none of above"},
            {"(-6) +(-5)"," (-6) -(-5)", "(6) +(-5) ","All"},
            {"35" , "34", "-34", "0"},
            {"18" ,"0" , "36" , "-18"},
            {"2" ,"5","10","120"},
            {"70","80","90","100"},
            {"175 cm" ,"100 cm" ,"163 cm" ,"123 cm"},
            {"0" , "4" , "8" , "-4"},
            {"0","5" , "-5", "10"},
            {"8", "-4" ,"4", "0"},
            { "8" ,"-8" , "0" , "4"},
            {"15"  ,"-15" ,"1" , "None of the above"},
            {"5" , "25" ,"-5" , "-25"},
            {"3" , "0" ,"1" , "-3"},
            {"0","1","2","4"},
            {"5","1","6","7"},
            {"p - 5 = 10","-p + 5 = 10","p + 5 = -10","p + 5 = 10"},
            {"q– 6 = -9","q– 6 = 9","-q– 6 = 9","none of above"},
            {"5","6","7","8"}
    };

    public String correctAnswer[] = {
            "-5 + (-1)",
            "-9 – (-1)",
            "5 + (-4)",
            "(-2) – (-9)",
            "(-7) + 1",
            "(-9) + 2",
            "Team B",
            "(-6) +(-5)",
            "-34",
            "0",
            "2",
            "70",
            "123 cm",
            "0",
            "5",
            "8",
            "0",
            "-15",
            "25",
            "-3",
            "2",
            "7",
            "p + 5 = 10",
            "q– 6 = 9",
            "5"

    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }

}
