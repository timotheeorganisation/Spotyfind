package com.example.blindtest.Classes;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

    @DatabaseTable( tableName = "T_Reponse")
    public class Reponse {
        @DatabaseField( columnName = "id", generatedId = true)
        private int id;
        @DatabaseField
        private String libelleReponse;
        @DatabaseField
        private Boolean boolReponse;
        @DatabaseField (canBeNull = false, foreign = true, foreignColumnName = "idQuestion", foreignAutoCreate = true)
        private Question question;

        public Reponse()
        {

        }
        public Reponse(String libelleReponse, Boolean boolReponse, Question question) {
            this.libelleReponse = libelleReponse;
            this.boolReponse = boolReponse;
            this.question = question;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLibelleReponse() {
            return libelleReponse;
        }

        public void setLibelleReponse(String libelleReponse) {
            this.libelleReponse = libelleReponse;
        }

        public Boolean getBoolReponse() {
            return boolReponse;
        }

        public void setBoolReponse(Boolean boolReponse) {
            this.boolReponse = boolReponse;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }
    }
