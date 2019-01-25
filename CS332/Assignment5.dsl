net Unnamed
{
 HEADER = 
  {
   ID = Unnamed;
   NAME = "Unnamed";
   COMMENT = "";
  };
 CREATION = 
  {
  };
 NUMSAMPLES = 10000;
 SCREEN = 
  {
   POSITION = 
    {
     CENTER_X = 0;
     CENTER_Y = 0;
     WIDTH = 76;
     HEIGHT = 36;
    };
   COLOR = 16250597;
   SELCOLOR = 12303291;
   FONT = 1;
   FONTCOLOR = 0;
   BORDERTHICKNESS = 3;
   BORDERCOLOR = 12255232;
  };
 WINDOWPOSITION = 
  {
   CENTER_X = 0;
   CENTER_Y = 0;
   WIDTH = 0;
   HEIGHT = 0;
  };
 BKCOLOR = 16777215;
 USER_PROPERTIES = 
  {
  };
 DOCUMENTATION = 
  {
  };
 SHOWAS = 3;

 node Burglary
  {
   TYPE = CPT;
   HEADER = 
    {
     ID = Burglary;
     NAME = "Burglary";
    };
   SCREEN = 
    {
     POSITION = 
      {
       CENTER_X = 40;
       CENTER_Y = 20;
       WIDTH = 76;
       HEIGHT = 36;
      };
     COLOR = 16250597;
     SELCOLOR = 12303291;
     FONT = 1;
     FONTCOLOR = 0;
     BORDERTHICKNESS = 1;
     BORDERCOLOR = 12255232;
    };
   USER_PROPERTIES = 
    {
    };
   DOCUMENTATION = 
    {
    };
   PARENTS = ();
   DEFINITION = 
    {
     NAMESTATES = (True, False);
     PROBABILITIES = (0.00100000, 0.99900000);
    };
   EXTRA_DEFINITION = 
    {
     DIAGNOSIS_TYPE = AUXILIARY;
     RANKED = FALSE;
     MANDATORY = FALSE;
     SETASDEFAULT = FALSE;
     SHOWAS = 4;
     FAULT_STATES = (0, 0);
     FAULT_NAMES = ("", "");
     FAULT_LABELS = ("", "");
     DEFAULT_STATE = 0;
     DOCUMENTATION = 
      {
      };
     DOCUMENTATION = 
      {
      };
     STATECOMMENTS = ("", "");
     STATEREPAIRINFO = ("", "");
     QUESTION = "";
    };
  };

 node Earthquake
  {
   TYPE = CPT;
   HEADER = 
    {
     ID = Earthquake;
     NAME = "Earthquake";
    };
   SCREEN = 
    {
     POSITION = 
      {
       CENTER_X = 235;
       CENTER_Y = 30;
       WIDTH = 76;
       HEIGHT = 36;
      };
     COLOR = 16250597;
     SELCOLOR = 12303291;
     FONT = 1;
     FONTCOLOR = 0;
     BORDERTHICKNESS = 1;
     BORDERCOLOR = 12255232;
    };
   USER_PROPERTIES = 
    {
    };
   DOCUMENTATION = 
    {
    };
   PARENTS = ();
   DEFINITION = 
    {
     NAMESTATES = (True, False);
     PROBABILITIES = (0.00200000, 0.99800000);
    };
   EXTRA_DEFINITION = 
    {
     DIAGNOSIS_TYPE = AUXILIARY;
     RANKED = FALSE;
     MANDATORY = FALSE;
     SETASDEFAULT = FALSE;
     SHOWAS = 4;
     FAULT_STATES = (0, 0);
     FAULT_NAMES = ("", "");
     FAULT_LABELS = ("", "");
     DEFAULT_STATE = 0;
     DOCUMENTATION = 
      {
      };
     DOCUMENTATION = 
      {
      };
     STATECOMMENTS = ("", "");
     STATEREPAIRINFO = ("", "");
     QUESTION = "";
    };
  };

 node Alarm
  {
   TYPE = CPT;
   HEADER = 
    {
     ID = Alarm;
     NAME = "Alarm";
    };
   SCREEN = 
    {
     POSITION = 
      {
       CENTER_X = 146;
       CENTER_Y = 92;
       WIDTH = 76;
       HEIGHT = 36;
      };
     COLOR = 16250597;
     SELCOLOR = 12303291;
     FONT = 1;
     FONTCOLOR = 0;
     BORDERTHICKNESS = 1;
     BORDERCOLOR = 12255232;
    };
   USER_PROPERTIES = 
    {
    };
   DOCUMENTATION = 
    {
    };
   PARENTS = (Burglary, Earthquake);
   DEFINITION = 
    {
     NAMESTATES = (True, False);
     PROBABILITIES = (0.95000000, 0.05000000, 0.94000000, 0.06000000, 
     0.29000000, 0.71000000, 0.00100000, 0.99900000);
    };
   EXTRA_DEFINITION = 
    {
     DIAGNOSIS_TYPE = AUXILIARY;
     RANKED = FALSE;
     MANDATORY = FALSE;
     SETASDEFAULT = FALSE;
     SHOWAS = 4;
     FAULT_STATES = (0, 0);
     FAULT_NAMES = ("", "");
     FAULT_LABELS = ("", "");
     DEFAULT_STATE = 0;
     DOCUMENTATION = 
      {
      };
     DOCUMENTATION = 
      {
      };
     STATECOMMENTS = ("", "");
     STATEREPAIRINFO = ("", "");
     QUESTION = "";
    };
  };

 node JohnCall
  {
   TYPE = CPT;
   HEADER = 
    {
     ID = JohnCall;
     NAME = "JohnCall";
    };
   SCREEN = 
    {
     POSITION = 
      {
       CENTER_X = 91;
       CENTER_Y = 180;
       WIDTH = 76;
       HEIGHT = 36;
      };
     COLOR = 16250597;
     SELCOLOR = 12303291;
     FONT = 1;
     FONTCOLOR = 0;
     BORDERTHICKNESS = 1;
     BORDERCOLOR = 12255232;
    };
   USER_PROPERTIES = 
    {
    };
   DOCUMENTATION = 
    {
    };
   PARENTS = (Alarm);
   DEFINITION = 
    {
     NAMESTATES = (True, False);
     PROBABILITIES = (0.90000000, 0.10000000, 0.05000000, 0.95000000);
    };
   EXTRA_DEFINITION = 
    {
     DIAGNOSIS_TYPE = AUXILIARY;
     RANKED = FALSE;
     MANDATORY = FALSE;
     SETASDEFAULT = FALSE;
     SHOWAS = 4;
     FAULT_STATES = (0, 0);
     FAULT_NAMES = ("", "");
     FAULT_LABELS = ("", "");
     DEFAULT_STATE = 0;
     DOCUMENTATION = 
      {
      };
     DOCUMENTATION = 
      {
      };
     STATECOMMENTS = ("", "");
     STATEREPAIRINFO = ("", "");
     QUESTION = "";
    };
  };

 node MaryCall
  {
   TYPE = CPT;
   HEADER = 
    {
     ID = MaryCall;
     NAME = "MaryCall";
    };
   SCREEN = 
    {
     POSITION = 
      {
       CENTER_X = 208;
       CENTER_Y = 191;
       WIDTH = 76;
       HEIGHT = 36;
      };
     COLOR = 16250597;
     SELCOLOR = 12303291;
     FONT = 1;
     FONTCOLOR = 0;
     BORDERTHICKNESS = 1;
     BORDERCOLOR = 12255232;
    };
   USER_PROPERTIES = 
    {
    };
   DOCUMENTATION = 
    {
    };
   PARENTS = (Alarm);
   DEFINITION = 
    {
     NAMESTATES = (True, False);
     PROBABILITIES = (0.70000000, 0.30000000, 0.01000000, 0.99000000);
    };
   EXTRA_DEFINITION = 
    {
     DIAGNOSIS_TYPE = AUXILIARY;
     RANKED = FALSE;
     MANDATORY = FALSE;
     SETASDEFAULT = FALSE;
     SHOWAS = 4;
     FAULT_STATES = (0, 0);
     FAULT_NAMES = ("", "");
     FAULT_LABELS = ("", "");
     DEFAULT_STATE = 0;
     DOCUMENTATION = 
      {
      };
     DOCUMENTATION = 
      {
      };
     STATECOMMENTS = ("", "");
     STATEREPAIRINFO = ("", "");
     QUESTION = "";
    };
  };
 OBSERVATION_COST = 
  {

   node Burglary
    {
     PARENTS = ();
     COSTS = (0.00000000);
    };

   node Earthquake
    {
     PARENTS = ();
     COSTS = (0.00000000);
    };

   node Alarm
    {
     PARENTS = ();
     COSTS = (0.00000000);
    };

   node JohnCall
    {
     PARENTS = ();
     COSTS = (0.00000000);
    };

   node MaryCall
    {
     PARENTS = ();
     COSTS = (0.00000000);
    };
  };
};
