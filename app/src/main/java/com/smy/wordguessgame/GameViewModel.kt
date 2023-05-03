package com.smy.wordguessgame

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val currentWord : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val score : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val bundleFromFragmentBToFragmentA = MutableLiveData<Bundle>()

    val removedWordList = mutableListOf<String>()



    init {
        score.value = 0
    }




    private val word_list:MutableList<String> = mutableListOf(
        "daughter","if","cry","red","probably","mail","in","excellent","guard","rocky","wheat","bite","touch","block","activity","major","breathing","it","vertical","slope","title","gas",
        "union","wagon","victory","temperature","frame","later","acres","now","process","jump","effort","size","believed","sitting","harbor","man","spell","form","tree","cell","birds","hot",
        "since","rabbit","hole","contain","late","men","box","breeze","soil","society","out","rush","empty","forward","one","studying","create","hat","little","examine","simplest","oxygen",
        "married","down","telephone","count","husband","situation","dangerous","steady","feathers","television","twelve","according","mixture","president","ring","lot","lying","sent","wire",
        "rule","knew","noted","seat","area","foreign","detail","expression","moon","through","basis","many","primitive","prize","spring","rising","store","said","engine","breathe","scientific",
        "composition","herd","win","monkey","promised","negative","separate","from","running","successful","neck","barn","column","once","time","tightly","glass","base","labor","until","they",
        "captured","chance","broke","consist","rays","purpose","changing","forty","silk","team","had","class","select","continued","desert","pie","mood","into","dinner","rubbed","thirty","flow","find","spider","straw","exist","opportunity","result","different","natural","structure","yellow","shine","return","length","needs","aside","exclaimed","sad","science","figure","failed","driver","stream","my","clay","dry","farmer","old","slave","below","over","course","rhythm","danger","save","ground","company","go","alike","see","local","customs","right","line","frighten","for","building","bring","long","why","gold","explain","dirt","especially","choice","brown","plus","current","missing","fully","goes","rather","bear","brave","women","related","chose","stared","opinion","spread","palace","angle","familiar","worse","lie","bar","round","directly",
        "universe","pony","plane","he","fewer","grew","feet","star","funny","construction","shown",
        "color","nails","citizen","nervous","contain","carried","year","connected","goose","tank","read","essential","island","name","everything","number","river","could","later","again",
        "yourself","needed","important","rabbit","fourth","follow","small","hunter","amount","trail","planning","truck","transportation","leader","corn","general","bound","idea","board","neighborhood","whale","individual","claws","solve","shut","failed","knowledge","arrive","gift","tonight","feature","dozen","nearby","introduced","easy","tie","death","wore","farmer","wing","soil","soft","sum","safety","neighbor","cake","everywhere","author","pick","onlinetools","dropped","managed","down","moving","promised","equal","especially","cowboy","chemical","wonderful","softly","travel","victory","stood","struck","ago","common","freedom","love","tin","paid","log","public","driver","beside","model","four","class","plain","tide","count","managed","key","club","gone","quarter","fifty","too","railroad","tip","drink","realize","lamp","length","someone","giving","fifteen","brain","pole","improve","piece","needed","press","chicken","muscle","with","yesterday","sugar","load","citizen","around","follow","victory","onto","living","station","contrast","paid","stove","airplane","fresh","rocket","underline","friendly","room","found","collect","pretty","tea","last","orbit","huge","slip","band","wear","string","using","my","political","alone","article","whistle","twelve","purpose","solve","straight","capital","sunlight","bound","bear","health","thrown","indeed","activity","chosen","smooth","great","substance","ocean","cross","spite","lower","discussion","recent","recent","skin","older","full","help","cheese",
        "brick","slept","tiny","liquid","story","location","circus","how","managed",
        "lake","by","bank","evidence","determine","laugh","began","dried","failed","center","branch","pie","press","complex","live","traffic","transportation","race","believed","life","unit","chicken","master","afternoon","image","crew","occur","how","seen","poem","slightly","egg",
        "research","higher","brown","cutting","between","property"
    )

    fun getWord() : String{
        val randomWord = word_list.random()
        currentWord.value = randomWord
        return randomWord
    }

    fun checkWord(answer:String) : Boolean{
        if(answer == currentWord.value){
            removeWord(answer)
            if(checkNextWordExists())
                getWord()
            score.value = score.value?.plus(1)
            return true
        }
        return false
    }

    fun resetGame(){
        score.value = 0
        word_list.addAll(removedWordList)
        getWord()
    }


    private fun checkNextWordExists() : Boolean {
        if(word_list.lastIndex == -1)
            return false
        return true
    }

    private fun removeWord(word:String){
        removedWordList.add(word)
        word_list.remove(word)
    }

}
