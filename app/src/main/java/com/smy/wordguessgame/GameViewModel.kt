package com.smy.wordguessgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val currentWord : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val score : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val removedWordList = mutableListOf<String>()

    init {
        score.value = 0
    }




    private val word_list:MutableList<String> = mutableListOf(
        "abed","abet","able","ably","abut","aced","aces","ache","achy","acid",
        "bale","balk","ball","balm","banc","band","bane","bang","bank","bans",
        "cake","calf","calk","call","calm","came","camp","cams","cane","cans","cant","cape","capo","caps","card","care","carp","cars","cart","casa","case","cash","cask","cast","cats","cave","cavy","caws","cede","cedi","ceil","cell","cent","cero","cert","cess","chap","char","chat","chef","chew","chic","chin","chip","chit","chon","chop","chow","chub","chug","chum","ciao","cine","cist","cite","city","clad","clam","clan","clap","claw","clay","clef","clip","clod","clog","clop","clot","cloy","club","clue","coal","coat","coax","cobs","coca","cock","coco","coda","code","cods","cogs","coif","coil","coin","coir","coke","cola","cold","coli","colt","coma","comb","come","comp","cone","cong","conk","cons","cook","cool","coon","coop","coot","cope","cops","copy","cord","core","corf","cork","corn","cosh","cost","cosy","cote","cots","coup","cove","cowl","cows","crab","crag","cram","crap","craw","crew","crib","crop","crow","crud","crux","cube","cubs","cued","cues","cuff","cull","cult","cups","curb","curd","cure","curl","curs","curt","cusp","cuss","cute","cuts","cyan","cyma","cyst","czar","dabs","dace","dado","dads","daff","daft","dais","dale","dame","damn","damp","dams","dang","dank","dare","dark","darn","dart","dash","data","date","daub","dawn","days","daze","dead","deaf","deal","dean","dear","debs","debt","deck","deed","deem","deep","deer","deft","defy","deil","dele","deli","dell","demi","demo","demy","dens","dent","deny","derv","desk","dews","dewy","dhow","dial","dibs","dice","dick","died","diem","dies","diet","digs","dike","dill","dime","dims","dine","ding","dink","dint","dips","dire","dirk","dirt","disc","dish","disk","diva","dive","dock","dodo","doer","does","doff","doge","dogs","dojo","dole","doll","dolt","dome","done","dong","dons","doom","door","dope","dopy","dorm","dory","dose","doss","dost","dote","doth","dots","dour","dove","down","doxy","doze","dozy","drab","drag","dram","drat","draw","dray","dreg","drew","drib","drip","drop","drub","drug","drum","dual","dubs","duck","duct","dude","duds","duel","dues","duet","duff","duke","dull","duly","dumb","dump","dune","dung",
        "dunk","duos","dupe","dusk","dust","duty","dyad","dyed","dyer","dyes",
        "dyne","each","earl","earn","ears","ease","east","easy","eats","eave",
        "ebbs","ebon","ecce","echo","ecol","ecru","edam","eddo","eddy","edge",
        "edgy","edit","eels","eely","egad","eggs","egos","eked","ekes","elks",
        "else","emir","emit","ends","envy","epic","epos","eras","ergo","erne",
        "errs","espy","etas","etch","etui","even","ever","eves","evil","ewer",
        "ewes","exam","exec","exit","expo","eyed","eyes","face","fact","fade",
        "fads","fags","fail","fain","fair","fake","fall","fame","fane","fang",
        "fans","fare","farm","faro","fart","fast","fate","fats","faun","faux","fawn","faze","fear","feat","feds","feed","feel","fees","feet","fell","felt","fend","fern","fess","fest","feta","fete","feud","fiat","fide","fief","fife","figs","file","fill","film","find","fine","fink","fins","fire","firm","fish","fist","fits","five","fizz","flab","flag","flak",
        "flam","flan","flap","flat","flaw","flax","flay","flea","fled","flee","flew","flex","flip","flit","floe","flog","flop","flow","flub","flue","flux","foal","foam","foci","foes","fogs","fogy","foil","fold","folk","fond","font","food","fool","foot","fops","ford","fore","fork","form","fort","foul","four","fowl","foxy","frae","frap","frat","fray","free","fret","frig","frit","froe","frog","from","fuck","fuel","full","fume","fumy","fund","funk","furl","furs","fury","fuse","fuss","fuzz"
        ,"gaff","gaga","gage","gags","gain","gait","gala","gale","gall","gals","game","gamp","gamy","gang","gaol","gape","gaps","garb","gash","gasp","gate","gaud","gaur","gave","gawk","gawp","gaze","gear","geek","geld","gels","gelt","gems","gene","gent","germ","gets","ghee","gibe","gift","gigs","gild","gill","gilt","gimp","gink","gins","gird","girl","giro","girt","gist","give","glad","glee","glen","glib","glob","glom","glow","glue","glum","glut","gnat","gnaw","goad","goal","goat","gobo","goby","gods","goer","goes","gold","golf","gone","gong","good","goof","gook","goon","gore","gory","gosh","gout","gown","grab","grad","grew","grey","grid","grim","grin","grip","grit","grog","grot","grow","grub","guan","guck","guff","gulf","gull","gulp","gums","gunk","guns","guru","gush","gust","guts","guys","gyms","gyre","gyro","hack","hade","haft","hail","hair","hajj","hake","hale","half","hall","halo","halt","hams","hand","hang","hank","hard","hare","hark","harm","harp","hart","hash","hasp","hast","hate","hath","hats","haul","have","hawk","hays","haze","hazy","head","heal","heap","hear","heat","heck","heed","heel","heft","heir","held","hell","helm","help","hemp","hems","hens","herb","herd","here","herm","hero","hers","hest","hewn","hews","hick","hide","high","hike","hill","hilt","hind","hint","hips","hire","hiss","hits","hive","hoar","hoax","hobo","hock","hoer","hoes","hogs","hold","hole","hols","holt","holy","home","homo","hone","honk","hood","hoof","hook","hoop","hoot","hope","hops","horn","hors","hose","host","hour","hove","howl","html","hubs","hued","hues","huff","huge","hugs","hula","hulk","hull","hump","hums","hung","hunk","hunt","hurl","hurt","hush","husk","huts","hymn","hype","hypo","iamb","ibex","ibid","ibis","iced","ices","icky","icon","idea","idem","ides","idle","idly","idol","iffy","ilea","ilex","ills","imam","impi","imps","inch","indo","info","inks","inky","inns","into","ions","iota","ipso","iris","irks","iron","isle","itch","item","itsy","jabs","jack","jade","jail","jamb","jams","jape","jarl","jars","java","jaws","jazz","jean","jeep","jeer","jell","jerk","jess","jest","jets","jibe","jiff","jigs","jilt","jink","jinn","jinx","jive","jobs","jock","joey","jogs","john","join","joke","jolt","josh","joss","jota","jots","jour","jowl","joys","juba","judo","jugs","juju","juke","jump","junk","jury","just","jute","kaka","kaki","kale","kali","kana","kart","kava","kayo","kcal","keel","keen","keep","kelp","keno","kept","kerb","kern","keys","khan","kick","kids","kike","kill","kiln","kilo","kilt","kina","kind","king","kink","kips","kiss","kite","kith","kits","kiwi","knag","knap","knar","knee","knew","knit","knob","knot","know","kola","kook","kudu","labs","lace","lack","lacy","lade","lads","lady","lags","laic","laid","lain","lair","lake","laky","lama","lamb","lame","lamp","land","lane","lank","laps","lard","lark","lash","lass","last","late","lath","laud","lava","lave","lawn","laws","lays","laze","lazy","lead","leaf","leak","lean","leap","leek","leer","lees","left","legs","lend","lens","lent","less","lest","lets","levy","lewd","liar","lice","lick","lido","lids","lied","lien","lies","lieu","life","lift","like","lilt","lily","limb","lime","limn","limo","limp","limy","line","link","lino","lint","lion","lips","lira","lire","lisp","list","live","load","loaf","loam","loan","lobe","lobo","loch","loci","lock","loco","lode","loft","loge","logo","logs", "logy","loin","loll","lone","long","look","loom","loon","loop",
        "loot","lope","lops","lord","lore","lose","loss","lost","lots","loud",
        "lout","love","lows","luau","lube","luck","luff","luge","lugs","lull",
        "lulu","lump","lung","lure","lurk","lush","lust","lute","lvii","lxii",
        "lxiv","lxix", "lxvi","lynx","lyre","mace","made","mage","magi","maid"
    )

    fun getWord(){
        val randomWord = word_list.random()
        currentWord.value = randomWord
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
