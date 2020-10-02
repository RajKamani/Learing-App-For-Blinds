package com.TheVision.tv;

public class Question {

    public String questions[] = {
            "Grand Central Terminal, Park Avenue, New York is the world's",
            "Which is a Programming Language?",
            "In COMAL language program, after name of procedure parameters must be in?",
            "Programming language COBOL works best for use in?",
            "Entomology is the science that studies",
            "Eritrea, which became the 182nd member of the UN in 1993, is in the continent of",
            "Garampani sanctuary is located at",
            "For which of the following disciplines is Nobel Prize awarded?",
            "Hitler party which came into power in 1933 is known as",
            "FFC stands for",
            "Fastest shorthand writer was",
            "Epsom (England) is the place associated with",
            "First human heart transplant operation conducted by Dr. Christiaan Barnard on Louis Washkansky, was conducted in",
            "Galileo was an Italian astronomer who",
            "Habeas Corpus Act 1679",
            "Exposure to sunlight helps a person improve his health because",
            "Golf player Vijay Singh belongs to which country?",
            "Guarantee to an exporter that the importer of his goods will pay immediately for the goods ordered by him, is known as",
            "First Afghan War took place in",
            "Gulf cooperation council was originally formed by",
            "First China War was fought between",
            "Dumping is",
            "For the Olympics and World Tournaments, the dimensions of basketball court are",
            "Federation Cup, World Cup, Allywyn International Trophy and Challenge Cup are awarded to winners of",
            "Each year World Red Cross and Red Crescent Day is celebrated on",
            "Famous sculptures depicting art of love built some time in 950 AD  1050 AD are",
            "Gravity setting chambers are used in industries to remove",
            "Guwahati High Court is the judicature of",
            "Friction can be reduced by changing from",
            "During eleventh Antarctic Expedition in Nov. 1991/March 1992 ____ was installe",
    };

    public String choices[][] = {
            {"largest railway station", "	highest railway station", "	longest railway station", "	None of the above"},
            {"HTML", " CSS", " PHP", " nothing"},
            {"Punction Marks", "Back-Slash", "Brackets", "Semi Colon"},
            {"Siemens Applications", "Student Applications", "Social Applications", "Commercial Applications"},
            {"Behavior of human beings", "Insects", "The origin and history of technical and scientific terms", "The formation of rocks"},
            { "Asia","Africa","Europe", "Australia"},
            {"Junagarh, Gujarat", "Diphu, Assam", "Kohima, Nagaland", "Gangtok, Sikkim"},
            {"Physics and Chemistry"," Physiology or Medicine Literature", "Peace and Economics ","All"},
            {"Labour Party" , "Nazi Party", "Ku-Klux-Klan", "Democratic Party"},
            {"Foreign Finance Corporation" ,"Film Finance Corporation" , "Federation of Football Council" , "None of the option"},
            {"Dr. G.  Bist" ,"J.R. Tata","J.M. Tagore","Khudada Khan"},
            {"Horse racing","Polo","Shooting","Snooker"},
            {"1958" ,"1968" ,"1967" ,"1922"},
            {"developed the telescope" , "discovered four satellites of Jupiter" , "discovered that the movement of pendulum produces a regular time measurement" , "All of the above"},
            {"states that no one was to be imprisoned without a writ or warrant stating the charge against him","provided facilities to a prisoner to obtain either speedy trial or release in bail" , "safeguarded the personal liberties of the people against arbitrary imprisonment by the king's orders", "All of the above"},
            {"the infrared light kills bacteria in the body", "resistance power increases" ,"the pigment cells in the skin get stimulated and produce a healthy tan", "the ultraviolet rays convert skin oil into Vitamin D"},
            { "USA" ,"Fiji" , "India" , "UK"},
            {"Letter of Credit (L/C)" , "laissezfaire" , "inflation" , "None of the above"},
            {"1838" , "1843" ,"1833" , "1839"},
            {"Bahrain, Kuwait, Oman, Qatar, Saudi Arabia and United Arab Emirates" , "Second World Nations" ,"Third World Nations" , "Fourth World Nations"},
            {"China and Britain","China and France","China and Egypt","China and Greek"},
            {"selling of goods abroad at a price well below the production cost at the home market price","the process by which the supply of a manufacture's product remains low in the domestic market, which batches him better price","prohibited by regulations of GATT","All of the above"},
            {"26 m x 14 m","28 m x 15 m","27 m x 16 m","28 m x 16 m"},
            {"Tennis","Volleyball","Basketball","Cricket"},
            {"May 8","May 18","June 8","June 18"},
            {"Khajuraho temples","Jama Masjid","Sun temple","Mahabalipuram temples"},
            {"SOx","NOx","suspended particulate matter","CO"},
            {"Nagaland","Arunachal Pradesh","Assam","All of the above"},
            {"sliding to rolling","rolling to sliding","potential energy to kinetic energy","dynamic to static"},
            {"SODAR (SOnic Detection And Ranging)","Second Permanent Station 'Maitree'","First permanent station 'Dakshin Gangotri'","None of the above"},
    };

    public String correctAnswer[] = {
            "largest railway station",
            "PHP",
            "Brackets",
            "Commercial Applications",
            "Insects",
            "Africa",
            "Diphu, Assam",
            "All",
            "Nazi Party",
            "Film Finance Corporation",
            "Dr. G.  Bist",
            "Horse racing",
            "1967",
            "All of the above",
            "All of the above",
            "the ultraviolet rays convert skin oil into Vitamin D",
            "Fiji",
            "Letter of Credit (L/C)",
            "1839",
            "Bahrain, Kuwait, Oman, Qatar, Saudi Arabia and United Arab Emirates",
            "China and Britain",
            "All of the above",
            "28 m x 15 m",
            "Volleyball",
            "May 8",
            "Khajuraho temples",
            "suspended particulate matter",
            "All of the above",
            "sliding to rolling",
            "SODAR (SOnic Detection And Ranging)",

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
