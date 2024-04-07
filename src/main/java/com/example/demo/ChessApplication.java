package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.Elements;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.demo.serviceImpl.ChessServiceImpl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker;


public class ChessApplication extends Application {
	
	public static ConfigurableApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		this.applicationContext = new SpringApplicationBuilder().sources(DemoApplication.class)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ChessServiceImpl().showChessScreen();
    }
    
    @Override
    public void stop() {
    	applicationContext.close();
    	Platform.exit();
    	System.exit(0);
    }
	
//    private static final String HTML_TEMPLATE = "<!DOCTYPE html>\n" +
//            "<html>\n" +
//            "<head>\n" +
//            "    <style>\n" +
//            "        body {\n" +
//            "            background-color: #f2f2f2;\n" +
//            "            font-family: Arial, sans-serif;\n" +
//            "            margin: 0;\n" +
//            "            padding: 20px;\n" +
//            "        }\n" +
//            "        h1 {\n" +
//            "            color: #333;\n" +
//            "        }\n" +
//            "        p {\n" +
//            "            color: #666;\n" +
//            "        }\n" +
//            "    </style>\n" +
//            "    <title>HTML to JavaFX</title>\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "%s\n" +
//            "</body>\n" +
//            "</html>";
//
//    private String htmlInput = "<html lang=\"en\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,shrink-to-fit=no\"><meta name=\"theme-color\" content=\"#000000\"><link rel=\"manifest\" href=\"/manifest.json\"><link rel=\"stylesheet\" href=\"/assets/css/bootstrap.min.css\"><script src=\"https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.6.15/browser-polyfill.min.js\"></script><script src=\"https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-MML-AM_HTMLorMML\" id=\"\"></script><link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.10.2/css/all.css\" integrity=\"sha384-rtJEYb85SiYWgfpCr0jn174XgJTn4rptSOQsMroFBPQSGLdOC5IbubP6lJ35qoM9\" crossorigin=\"anonymous\"><title>Quizky Testengine</title><style>@media print{*{display:none!important}}</style><link href=\"/static/css/main.cdfb9780.chunk.css\" rel=\"stylesheet\"><style type=\"text/css\">.MathJax_Hover_Frame {border-radius: .25em; -webkit-border-radius: .25em; -moz-border-radius: .25em; -khtml-border-radius: .25em; box-shadow: 0px 0px 15px #83A; -webkit-box-shadow: 0px 0px 15px #83A; -moz-box-shadow: 0px 0px 15px #83A; -khtml-box-shadow: 0px 0px 15px #83A; border: 1px solid #A6D ! important; display: inline-block; position: absolute}\r\n"
//    		+ ".MathJax_Menu_Button .MathJax_Hover_Arrow {position: absolute; cursor: pointer; display: inline-block; border: 2px solid #AAA; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px; font-family: 'Courier New',Courier; font-size: 9px; color: #F0F0F0}\r\n"
//    		+ ".MathJax_Menu_Button .MathJax_Hover_Arrow span {display: block; background-color: #AAA; border: 1px solid; border-radius: 3px; line-height: 0; padding: 4px}\r\n"
//    		+ ".MathJax_Hover_Arrow:hover {color: white!important; border: 2px solid #CCC!important}\r\n"
//    		+ ".MathJax_Hover_Arrow:hover span {background-color: #CCC!important}\r\n"
//    		+ "</style><style type=\"text/css\">#MathJax_About {position: fixed; left: 50%; width: auto; text-align: center; border: 3px outset; padding: 1em 2em; background-color: #DDDDDD; color: black; cursor: default; font-family: message-box; font-size: 120%; font-style: normal; text-indent: 0; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; word-wrap: normal; white-space: nowrap; float: none; z-index: 201; border-radius: 15px; -webkit-border-radius: 15px; -moz-border-radius: 15px; -khtml-border-radius: 15px; box-shadow: 0px 10px 20px #808080; -webkit-box-shadow: 0px 10px 20px #808080; -moz-box-shadow: 0px 10px 20px #808080; -khtml-box-shadow: 0px 10px 20px #808080; filter: progid:DXImageTransform.Microsoft.dropshadow(OffX=2, OffY=2, Color='gray', Positive='true')}\r\n"
//    		+ "#MathJax_About.MathJax_MousePost {outline: none}\r\n"
//    		+ ".MathJax_Menu {position: absolute; background-color: white; color: black; width: auto; padding: 2px; border: 1px solid #CCCCCC; margin: 0; cursor: default; font: menu; text-align: left; text-indent: 0; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; word-wrap: normal; white-space: nowrap; float: none; z-index: 201; box-shadow: 0px 10px 20px #808080; -webkit-box-shadow: 0px 10px 20px #808080; -moz-box-shadow: 0px 10px 20px #808080; -khtml-box-shadow: 0px 10px 20px #808080; filter: progid:DXImageTransform.Microsoft.dropshadow(OffX=2, OffY=2, Color='gray', Positive='true')}\r\n"
//    		+ ".MathJax_MenuItem {padding: 2px 2em; background: transparent}\r\n"
//    		+ ".MathJax_MenuArrow {position: absolute; right: .5em; padding-top: .25em; color: #666666; font-size: .75em}\r\n"
//    		+ ".MathJax_MenuActive .MathJax_MenuArrow {color: white}\r\n"
//    		+ ".MathJax_MenuArrow.RTL {left: .5em; right: auto}\r\n"
//    		+ ".MathJax_MenuCheck {position: absolute; left: .7em}\r\n"
//    		+ ".MathJax_MenuCheck.RTL {right: .7em; left: auto}\r\n"
//    		+ ".MathJax_MenuRadioCheck {position: absolute; left: 1em}\r\n"
//    		+ ".MathJax_MenuRadioCheck.RTL {right: 1em; left: auto}\r\n"
//    		+ ".MathJax_MenuLabel {padding: 2px 2em 4px 1.33em; font-style: italic}\r\n"
//    		+ ".MathJax_MenuRule {border-top: 1px solid #CCCCCC; margin: 4px 1px 0px}\r\n"
//    		+ ".MathJax_MenuDisabled {color: GrayText}\r\n"
//    		+ ".MathJax_MenuActive {background-color: Highlight; color: HighlightText}\r\n"
//    		+ ".MathJax_MenuDisabled:focus, .MathJax_MenuLabel:focus {background-color: #E8E8E8}\r\n"
//    		+ ".MathJax_ContextMenu:focus {outline: none}\r\n"
//    		+ ".MathJax_ContextMenu .MathJax_MenuItem:focus {outline: none}\r\n"
//    		+ "#MathJax_AboutClose {top: .2em; right: .2em}\r\n"
//    		+ ".MathJax_Menu .MathJax_MenuClose {top: -10px; left: -10px}\r\n"
//    		+ ".MathJax_MenuClose {position: absolute; cursor: pointer; display: inline-block; border: 2px solid #AAA; border-radius: 18px; -webkit-border-radius: 18px; -moz-border-radius: 18px; -khtml-border-radius: 18px; font-family: 'Courier New',Courier; font-size: 24px; color: #F0F0F0}\r\n"
//    		+ ".MathJax_MenuClose span {display: block; background-color: #AAA; border: 1.5px solid; border-radius: 18px; -webkit-border-radius: 18px; -moz-border-radius: 18px; -khtml-border-radius: 18px; line-height: 0; padding: 8px 0 6px}\r\n"
//    		+ ".MathJax_MenuClose:hover {color: white!important; border: 2px solid #CCC!important}\r\n"
//    		+ ".MathJax_MenuClose:hover span {background-color: #CCC!important}\r\n"
//    		+ ".MathJax_MenuClose:hover:focus {outline: none}\r\n"
//    		+ "</style><style type=\"text/css\">.MathJax_Preview .MJXf-math {color: inherit!important}\r\n"
//    		+ "</style><style type=\"text/css\">.MJX_Assistive_MathML {position: absolute!important; top: 0; left: 0; clip: rect(1px, 1px, 1px, 1px); padding: 1px 0 0 0!important; border: 0!important; height: 1px!important; width: 1px!important; overflow: hidden!important; display: block!important; -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none}\r\n"
//    		+ ".MJX_Assistive_MathML.MJX_Assistive_MathML_Block {width: 100%!important}\r\n"
//    		+ "</style><style type=\"text/css\">#MathJax_Zoom {position: absolute; background-color: #F0F0F0; overflow: auto; display: block; z-index: 301; padding: .5em; border: 1px solid black; margin: 0; font-weight: normal; font-style: normal; text-align: left; text-indent: 0; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; word-wrap: normal; white-space: nowrap; float: none; -webkit-box-sizing: content-box; -moz-box-sizing: content-box; box-sizing: content-box; box-shadow: 5px 5px 15px #AAAAAA; -webkit-box-shadow: 5px 5px 15px #AAAAAA; -moz-box-shadow: 5px 5px 15px #AAAAAA; -khtml-box-shadow: 5px 5px 15px #AAAAAA; filter: progid:DXImageTransform.Microsoft.dropshadow(OffX=2, OffY=2, Color='gray', Positive='true')}\r\n"
//    		+ "#MathJax_ZoomOverlay {position: absolute; left: 0; top: 0; z-index: 300; display: inline-block; width: 100%; height: 100%; border: 0; padding: 0; margin: 0; background-color: white; opacity: 0; filter: alpha(opacity=0)}\r\n"
//    		+ "#MathJax_ZoomFrame {position: relative; display: inline-block; height: 0; width: 0}\r\n"
//    		+ "#MathJax_ZoomEventTrap {position: absolute; left: 0; top: 0; z-index: 302; display: inline-block; border: 0; padding: 0; margin: 0; background-color: white; opacity: 0; filter: alpha(opacity=0)}\r\n"
//    		+ "</style><style type=\"text/css\">.MathJax_Preview {color: #888}\r\n"
//    		+ "#MathJax_Message {position: fixed; left: 1em; bottom: 1.5em; background-color: #E6E6E6; border: 1px solid #959595; margin: 0px; padding: 2px 8px; z-index: 102; color: black; font-size: 80%; width: auto; white-space: nowrap}\r\n"
//    		+ "#MathJax_MSIE_Frame {position: absolute; top: 0; left: 0; width: 0px; z-index: 101; border: 0px; margin: 0px; padding: 0px}\r\n"
//    		+ ".MathJax_Error {color: #CC0000; font-style: italic}\r\n"
//    		+ "</style><script charset=\"utf-8\" src=\"/static/js/0.5413d402.chunk.js\"></script><script charset=\"utf-8\" src=\"/static/js/TokenPage.bd892fd1.chunk.js\"></script><style type=\"text/css\">.MJXp-script {font-size: .8em}\r\n"
//    		+ ".MJXp-right {-webkit-transform-origin: right; -moz-transform-origin: right; -ms-transform-origin: right; -o-transform-origin: right; transform-origin: right}\r\n"
//    		+ ".MJXp-bold {font-weight: bold}\r\n"
//    		+ ".MJXp-italic {font-style: italic}\r\n"
//    		+ ".MJXp-scr {font-family: MathJax_Script,'Times New Roman',Times,STIXGeneral,serif}\r\n"
//    		+ ".MJXp-frak {font-family: MathJax_Fraktur,'Times New Roman',Times,STIXGeneral,serif}\r\n"
//    		+ ".MJXp-sf {font-family: MathJax_SansSerif,'Times New Roman',Times,STIXGeneral,serif}\r\n"
//    		+ ".MJXp-cal {font-family: MathJax_Caligraphic,'Times New Roman',Times,STIXGeneral,serif}\r\n"
//    		+ ".MJXp-mono {font-family: MathJax_Typewriter,'Times New Roman',Times,STIXGeneral,serif}\r\n"
//    		+ ".MJXp-largeop {font-size: 150%}\r\n"
//    		+ ".MJXp-largeop.MJXp-int {vertical-align: -.2em}\r\n"
//    		+ ".MJXp-math {display: inline-block; line-height: 1.2; text-indent: 0; font-family: 'Times New Roman',Times,STIXGeneral,serif; white-space: nowrap; border-collapse: collapse}\r\n"
//    		+ ".MJXp-display {display: block; text-align: center; margin: 1em 0}\r\n"
//    		+ ".MJXp-math span {display: inline-block}\r\n"
//    		+ ".MJXp-box {display: block!important; text-align: center}\r\n"
//    		+ ".MJXp-box:after {content: \" \"}\r\n"
//    		+ ".MJXp-rule {display: block!important; margin-top: .1em}\r\n"
//    		+ ".MJXp-char {display: block!important}\r\n"
//    		+ ".MJXp-mo {margin: 0 .15em}\r\n"
//    		+ ".MJXp-mfrac {margin: 0 .125em; vertical-align: .25em}\r\n"
//    		+ ".MJXp-denom {display: inline-table!important; width: 100%}\r\n"
//    		+ ".MJXp-denom > * {display: table-row!important}\r\n"
//    		+ ".MJXp-surd {vertical-align: top}\r\n"
//    		+ ".MJXp-surd > * {display: block!important}\r\n"
//    		+ ".MJXp-script-box > *  {display: table!important; height: 50%}\r\n"
//    		+ ".MJXp-script-box > * > * {display: table-cell!important; vertical-align: top}\r\n"
//    		+ ".MJXp-script-box > *:last-child > * {vertical-align: bottom}\r\n"
//    		+ ".MJXp-script-box > * > * > * {display: block!important}\r\n"
//    		+ ".MJXp-mphantom {visibility: hidden}\r\n"
//    		+ ".MJXp-munderover {display: inline-table!important}\r\n"
//    		+ ".MJXp-over {display: inline-block!important; text-align: center}\r\n"
//    		+ ".MJXp-over > * {display: block!important}\r\n"
//    		+ ".MJXp-munderover > * {display: table-row!important}\r\n"
//    		+ ".MJXp-mtable {vertical-align: .25em; margin: 0 .125em}\r\n"
//    		+ ".MJXp-mtable > * {display: inline-table!important; vertical-align: middle}\r\n"
//    		+ ".MJXp-mtr {display: table-row!important}\r\n"
//    		+ ".MJXp-mtd {display: table-cell!important; text-align: center; padding: .5em 0 0 .5em}\r\n"
//    		+ ".MJXp-mtr > .MJXp-mtd:first-child {padding-left: 0}\r\n"
//    		+ ".MJXp-mtr:first-child > .MJXp-mtd {padding-top: 0}\r\n"
//    		+ ".MJXp-mlabeledtr {display: table-row!important}\r\n"
//    		+ ".MJXp-mlabeledtr > .MJXp-mtd:first-child {padding-left: 0}\r\n"
//    		+ ".MJXp-mlabeledtr:first-child > .MJXp-mtd {padding-top: 0}\r\n"
//    		+ ".MJXp-merror {background-color: #FFFF88; color: #CC0000; border: 1px solid #CC0000; padding: 1px 3px; font-style: normal; font-size: 90%}\r\n"
//    		+ ".MJXp-scale0 {-webkit-transform: scaleX(.0); -moz-transform: scaleX(.0); -ms-transform: scaleX(.0); -o-transform: scaleX(.0); transform: scaleX(.0)}\r\n"
//    		+ ".MJXp-scale1 {-webkit-transform: scaleX(.1); -moz-transform: scaleX(.1); -ms-transform: scaleX(.1); -o-transform: scaleX(.1); transform: scaleX(.1)}\r\n"
//    		+ ".MJXp-scale2 {-webkit-transform: scaleX(.2); -moz-transform: scaleX(.2); -ms-transform: scaleX(.2); -o-transform: scaleX(.2); transform: scaleX(.2)}\r\n"
//    		+ ".MJXp-scale3 {-webkit-transform: scaleX(.3); -moz-transform: scaleX(.3); -ms-transform: scaleX(.3); -o-transform: scaleX(.3); transform: scaleX(.3)}\r\n"
//    		+ ".MJXp-scale4 {-webkit-transform: scaleX(.4); -moz-transform: scaleX(.4); -ms-transform: scaleX(.4); -o-transform: scaleX(.4); transform: scaleX(.4)}\r\n"
//    		+ ".MJXp-scale5 {-webkit-transform: scaleX(.5); -moz-transform: scaleX(.5); -ms-transform: scaleX(.5); -o-transform: scaleX(.5); transform: scaleX(.5)}\r\n"
//    		+ ".MJXp-scale6 {-webkit-transform: scaleX(.6); -moz-transform: scaleX(.6); -ms-transform: scaleX(.6); -o-transform: scaleX(.6); transform: scaleX(.6)}\r\n"
//    		+ ".MJXp-scale7 {-webkit-transform: scaleX(.7); -moz-transform: scaleX(.7); -ms-transform: scaleX(.7); -o-transform: scaleX(.7); transform: scaleX(.7)}\r\n"
//    		+ ".MJXp-scale8 {-webkit-transform: scaleX(.8); -moz-transform: scaleX(.8); -ms-transform: scaleX(.8); -o-transform: scaleX(.8); transform: scaleX(.8)}\r\n"
//    		+ ".MJXp-scale9 {-webkit-transform: scaleX(.9); -moz-transform: scaleX(.9); -ms-transform: scaleX(.9); -o-transform: scaleX(.9); transform: scaleX(.9)}\r\n"
//    		+ ".MathJax_PHTML .noError {vertical-align: ; font-size: 90%; text-align: left; color: black; padding: 1px 3px; border: 1px solid}\r\n"
//    		+ "</style><script charset=\"utf-8\" src=\"/static/js/1.6a5576af.chunk.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"/static/css/2.f53323e0.chunk.css\"><script charset=\"utf-8\" src=\"/static/js/2.9f313ed6.chunk.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"/static/css/5.5fe3b6d4.chunk.css\"><script charset=\"utf-8\" src=\"/static/js/5.239aa213.chunk.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"/static/css/Catlayout.438eda19.chunk.css\"><script charset=\"utf-8\" src=\"/static/js/Catlayout.26c0e34e.chunk.js\"></script></head><body oncopy=\"return!1\" oncut=\"return!1\" onpaste=\"return!1\" ondragstart=\"return!1\" ondrop=\"return!1\" class=\"catbody\"><div id=\"MathJax_Message\" style=\"display: none;\"></div><noscript>You need to enable JavaScript to run this app.</noscript><div id=\"root\"><div class=\"start-header\"></div><div class=\"topbar\"><div class=\"tb-head\">SimCAT 1 2023</div><div class=\"tb-buttons\"><div class=\"tp-link\" role=\"presentation\"><span class=\"q-icon\"></span><span>Question Paper</span></div><div class=\"tp-link\" role=\"presentation\"><span class=\"i-icon\"></span><span>Instructions</span></div></div></div><div class=\"ct-header\"><div class=\"ct-left\"><div class=\"ct-sections-block\"><div class=\"group-arrow-left-disabled\"></div><div class=\"ct-sections\"><div title=\"Verbal Ability &amp; Reading Comprehension\" class=\"ct-section-item \" role=\"presentation\"><span>Verbal Ability &amp; Reading Comprehension</span><span class=\"subject_instruction_icon1\"></span><div class=\"ct-section-dropdown\"><div class=\"ct-drp-title\">Verbal Ability &amp; Reading Comprehension</div><div class=\"ct-legend-block mt-2\"><div class=\"legend-item lg-answered mb-2\"><span>2</span> Answered</div><div class=\"legend-item lg-not_answered mb-2\"><span>18</span> Not Answered</div><div class=\"legend-item lg-not_visited mb-2\"><span>4</span> Not Visited</div><div class=\"legend-item lg-review mb-2\"><span>0</span> Marked for Review</div><div class=\"legend-item lg-review_answered mb-2\"><span>0</span> Answered &amp; Marked for Review (will be considered for evaluation)</div></div></div></div><div title=\"Data Interpretation &amp; Logical Reasoning\" class=\"ct-section-item currentSectionSelected\" role=\"presentation\"><span>Data Interpretation &amp; Logical Reasoning</span><span class=\"subject_instruction_icon1\"></span><div class=\"ct-section-dropdown\"><div class=\"ct-drp-title\">Data Interpretation &amp; Logical Reasoning</div><div class=\"ct-legend-block mt-2\"><div class=\"legend-item lg-answered mb-2\"><span>0</span> Answered</div><div class=\"legend-item lg-not_answered mb-2\"><span>3</span> Not Answered</div><div class=\"legend-item lg-not_visited mb-2\"><span>17</span> Not Visited</div><div class=\"legend-item lg-review mb-2\"><span>0</span> Marked for Review</div><div class=\"legend-item lg-review_answered mb-2\"><span>0</span> Answered &amp; Marked for Review (will be considered for evaluation)</div></div></div></div><div title=\"Quantitative Ability\" class=\"ct-section-item \" role=\"presentation\"><span>Quantitative Ability</span><span class=\"subject_instruction_icon1\"></span><div class=\"ct-section-dropdown\"><div class=\"ct-drp-title\">Quantitative Ability</div><div class=\"ct-legend-block mt-2\"><div class=\"legend-item lg-answered mb-2\"><span>0</span> Answered</div><div class=\"legend-item lg-not_answered mb-2\"><span>0</span> Not Answered</div><div class=\"legend-item lg-not_visited mb-2\"><span>22</span> Not Visited</div><div class=\"legend-item lg-review mb-2\"><span>0</span> Marked for Review</div><div class=\"legend-item lg-review_answered mb-2\"><span>0</span> Answered &amp; Marked for Review (will be considered for evaluation)</div></div></div></div></div><div class=\"group-arrow-right-disabled\"></div><div class=\"ct-icons\"><span class=\"calc-icon\" role=\"presentation\"></span></div></div><div class=\"ct-timer-block\"><div class=\"ct-timer-left\">Section</div><div class=\"ct-timer-right\"><span class=\"mr-1\"> Time Left :</span><span id=\"timer\"><span>00</span>:<span>15</span>:<span>40</span></span></div></div><div class=\"ct-marks-sections\"><span class=\"ct-mark-right\">Marks for correct answer <span class=\"text-success\">3</span></span><span class=\"mr-1 ml-1\">|</span><span>Negative Marks <span class=\"text-danger\"> 1</span></span></div></div><div class=\"ct-right\"><div class=\"ct-profile-image\"><img src=\"https://crampete.s3.ap-south-1.amazonaws.com/testengine-items/catlayout/NewCandidateImage.jpg\" alt=\"profile\"></div><div class=\"ct-profile-details\"><div class=\"ct-username\">Saurav Yadav</div></div></div></div><div class=\"testengine catlayout imsLayout \"><div class=\"RightBlock\"><div class=\"question-main-block font-0\"><div class=\"question-block\"><div class=\"question-number\"><div class=\"qq-title\">Question No. 6</div></div><div class=\"cat2-item null\"><div class=\"item-passage null\"><p class=\"passage\"><p><span style=\"font-family: Calibri;\"><strong>Refer to the data below and answer the questions that follow.</strong></span> <br><br><span style=\"font-family: Calibri;\">‘</span><span style=\"font-family: Calibri; color: #000000;\">Skye Coaching</span><span style=\"font-family: Calibri;\">’</span><span style=\"font-family: Calibri; color: #000000;\"> offers private tuition to school students. The Kolkata centre operates from 9 am to 7 pm everyday, during which it conducts six </span><span style=\"font-family: Calibri;\">simultaneous </span><span style=\"font-family: Calibri; color: #000000;\">classes every hour with sessions of duration of one hour</span><span style=\"font-family: Calibri;\"> each without any break. Class timings are divided into two groups – ‘Day’ classes (9 am – 3 pm) and ‘Evening’ classes (3 pm – 7 pm). </span><span style=\"font-family: Calibri; background-color: white;\">The last session of ‘Day’ class starts at 2 pm and ends at 3 pm. Similarly, the last session of ‘Evening’ class starts at 6 pm and ends at 7 pm.</span><span style=\"font-family: Calibri; font-size: 14px; background-color: white;\"> </span><span style=\"font-family: Calibri;\"> Exactly ten teachers teach every day. Every teacher teaches in an equal number of sessions per day.</span> <br><br><span style=\"font-family: Calibri;\">Exactly five teachers out of ten teach in six consecutive hours.&nbsp; The order in which they st</span><span style=\"font-family: Calibri;\">art their first classes in the </span><span style=\"font-family: Calibri;\">morning are Falguni Ma’am, Govind Sir, Hansika Ma’am, Ispa Ma’am, and Jayant Sir. During the ‘Day’ classes, Arijit Sir teaches in the first and the last session only and Bhaskar Sir teaches in the first two hours only. Chaitali Ma’am teaches in the first three and the last three sessions of the ‘Day’ and ‘Evening’ classes respectively. Debdeep Sir, being the Chief Mentor of the </span><span style=\"font-family: Calibri;\">centre</span><span style=\"font-family: Calibri;\">, remains busy with administrative activities and so, he doesn’t teach from 1 pm to 5 pm. Eeshani Ma’am does not teach between 2 pm and 6 pm.</span> <br><br><span style=\"font-family: Calibri;\">‘</span><span style=\"font-family: Calibri; color: #000000;\">Skye Coaching</span><span style=\"font-family: Calibri;\">’</span><span style=\"font-family: Calibri; color: #000000;\"> uses a mobile app for collecting student-feedback data just after every session. The data is processed by its unique algorithm whose output is three distinct best performing teachers&nbsp; assigned with five points (the best), three </span><span style=\"font-family: Calibri;\">points</span><span style=\"font-family: Calibri; color: #000000;\"> (second best) , and one point (</span><span style=\"font-family: Calibri;\">third best</span><span style=\"font-family: Calibri; color: #000000;\">) respectively. The following table shows the number of points earned for the 'Day' classes as well as the cumulative number of points earned for all the classes of the day by all the teachers.&nbsp;</span></p>\r\n"
//    		+ "<p><img src=\"https://crampete.s3.ap-south-1.amazonaws.com/uploads/ims/2023/4/1682510349507_2604_1.JPG\" alt=\"\" width=\"395\" height=\"249\">&nbsp;<br><span style=\"font-family: Calibri; color: #000000;\">Further it is known that</span><span style=\"font-family: Calibri;\">:</span> <br><span style=\"font-family: Calibri; color: #000000;\">1.</span> Falguni Ma’am never became one of the three best performing teachers in two consecutive sessions on that day. <br><span style=\"font-family: Calibri; color: #000000;\">2.</span> Arijit Sir, who never became one of the three best performing teachers in two consecutive sessions, received more points than Ispa Ma’am in one of the sessions when <span style=\"font-family: Calibri; color: #000000;\">both of them</span><span style=\"font-family: Calibri; color: #000000;\"> received some points.</span></p></p></div><div class=\"item-content null\"><p class=\"item\">Which teachers received points for the last session of the <span style=\"font-family: Calibri;\">‘Evening’ classes</span><span style=\"font-family: Calibri;color: #000000;\">?</span></p><div class=\"options\"><div class=\"optionitem\"><input type=\"radio\" name=\"q-16817314416940\" id=\"option-168173144169400\" value=\"168173144169400\"><label for=\"option-168173144169400\" class=\"optionLabel\"><span style=\"font-family: Calibri;color: #000000;\">Debdeep, Eeshani, Jayant</span></label></div><div class=\"optionitem\"><input type=\"radio\" name=\"q-16817314416940\" id=\"option-168173144169401\" value=\"168173144169401\"><label for=\"option-168173144169401\" class=\"optionLabel\"><span style=\"font-family: Calibri;\"></span>	<span style=\"font-family: Calibri;color: #000000;\">Arijit, Eeshani, Jayant</span></label></div><div class=\"optionitem\"><input type=\"radio\" name=\"q-16817314416940\" id=\"option-168173144169402\" value=\"168173144169402\"><label for=\"option-168173144169402\" class=\"optionLabel\"><span style=\"font-family: Calibri;color: #000000;\"></span>	Arijit, Debdeep, Jayant</label></div><div class=\"optionitem\"><input type=\"radio\" name=\"q-16817314416940\" id=\"option-168173144169403\" value=\"168173144169403\"><label for=\"option-168173144169403\" class=\"optionLabel\"><span style=\"font-family: Calibri;color: #000000;\"></span>	Arijit, Bhaskar, Jayant</label></div></div></div></div><div class=\"text-center show-mobile\"></div></div></div></div><div class=\"LeftBlock \"><button class=\"toggle-side-bar-btn\" type=\"button\">&gt;</button><div class=\"question-pallet\"><div class=\"Legend\"><div class=\"legend-block\"><div class=\"legend-item lg-answered\"><span>0</span> Answered</div><div class=\"legend-item lg-not_answered\"><span>3</span> Not Answered</div><div class=\"legend-item lg-not_visited\"><span>17</span> Not Visited</div><div class=\"legend-item lg-review\"><span>0</span> Marked for Review</div><div class=\"legend-item lg-review_answered\"><span>0</span> Answered &amp; Marked for Review (will be considered for evaluation)</div></div></div><div class=\"pallet-section-title\"><div class=\"qp-title\">Data Interpretation &amp; Logical Reasoning</div><div class=\"qp-label\">Choose a Question</div></div><div class=\"pallet-list-body\"><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_answered\" id=\"qp-168173144170015\">1</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_answered\" id=\"qp-168173144170116\">2</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170117\">3</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170118\">4</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170119\">5</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_answered\" id=\"qp-16817314416940\">6</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416981\">7</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416982\">8</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416983\">9</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416984\">10</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416985\">11</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416996\">12</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416997\">13</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416998\">14</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-16817314416999\">15</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144169910\">16</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170011\">17</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170012\">18</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170013\">19</span></div><div role=\"presentation\" class=\"pallet-item\"><span class=\"not_visited\" id=\"qp-168173144170014\">20</span></div></div></div></div><div class=\"offline\"></div></div><div class=\"ct-footer-block\"><div class=\"ct-footer-left\"><div class=\"footer-block hidden-mobile\"><button type=\"button\" class=\"btn btn-mark\">Mark for Review &amp; Next</button><button type=\"button\" class=\"btn btn-clr\">Clear Response</button><button class=\"btn btn-save-next\" type=\"button\">Save &amp; Next</button></div><div class=\"footer-block footer-border show-mobile\"><button type=\"button\" class=\"mob-btn-primary mr-2\">Mark »</button><button type=\"button\" class=\"btn mob-btn-submit\">Save &amp; Next</button></div></div><div class=\"ct-footer-right\"></div></div></div><script type=\"text/javascript\" src=\"https://crampete.s3.ap-south-1.amazonaws.com/js/iframeResizer.contentWindow.min.js\"></script><script>!function(c){function e(e){for(var t,a,o=e[0],r=e[1],n=e[2],u=0,f=[];u<o.length;u++)a=o[u],s[a]&&f.push(s[a][0]),s[a]=0;for(t in r)Object.prototype.hasOwnProperty.call(r,t)&&(c[t]=r[t]);for(p&&p(e);f.length;)f.shift()();return d.push.apply(d,n||[]),i()}function i(){for(var e,t=0;t<d.length;t++){for(var a=d[t],o=!0,r=1;r<a.length;r++){var n=a[r];0!==s[n]&&(o=!1)}o&&(d.splice(t--,1),e=b(b.s=a[0]))}return e}var a={},l={34:0},s={34:0},d=[];function b(e){if(a[e])return a[e].exports;var t=a[e]={i:e,l:!1,exports:{}};return c[e].call(t.exports,t,t.exports,b),t.l=!0,t.exports}b.e=function(d){var e=[];l[d]?e.push(l[d]):0!==l[d]&&{2:1,5:1,6:1,7:1,8:1,9:1,10:1,12:1,13:1,14:1,15:1,16:1,17:1,18:1,19:1,20:1,21:1,22:1,23:1,24:1,26:1,27:1,28:1,29:1,31:1,32:1,35:1,36:1,38:1}[d]&&e.push(l[d]=new Promise(function(e,o){for(var t=\"static/css/\"+({6:\"CSIRLayout\",7:\"Cat2Revise\",8:\"CatLayout\",9:\"Catlayout\",10:\"CmatLayout\",11:\"Completed\",12:\"Default\",13:\"DefaultRevise\",14:\"Duolingo\",15:\"GRERevise\",16:\"GRElayout\",17:\"IbpsLayout\",18:\"IbpsRevise\",19:\"MathGymLayout\",20:\"MobileReport\",21:\"NLATLayout\",22:\"NMATAdaptive\",23:\"NmatLayout\",24:\"PTELayout\",25:\"PageNotFound\",26:\"Report\",27:\"SatLayout\",28:\"SatLayoutNew\",29:\"SatRevise\",30:\"TokenPage\",31:\"XatLayout\",32:\"XatRevise\"}[d]||d)+\".\"+{0:\"31d6cfe0\",1:\"31d6cfe0\",2:\"f53323e0\",3:\"31d6cfe0\",4:\"31d6cfe0\",5:\"5fe3b6d4\",6:\"087fa16f\",7:\"865a2fdf\",8:\"087fa16f\",9:\"438eda19\",10:\"4a5bf494\",11:\"31d6cfe0\",12:\"b54a88ae\",13:\"49ca7fa7\",14:\"c6f176a6\",15:\"00f83d9b\",16:\"bb177fc8\",17:\"41f9bcc7\",18:\"fe39643c\",19:\"89ec377e\",20:\"61cca19c\",21:\"4393bf32\",22:\"366b9426\",23:\"393798b1\",24:\"72051f44\",25:\"31d6cfe0\",26:\"3d44fa26\",27:\"52f869e6\",28:\"52f869e6\",29:\"702a81f2\",30:\"31d6cfe0\",31:\"169811bb\",32:\"51930204\",35:\"5fe3b6d4\",36:\"5fe3b6d4\",38:\"f01b737b\"}[d]+\".chunk.css\",r=b.p+t,a=document.getElementsByTagName(\"link\"),n=0;n<a.length;n++){var u=(c=a[n]).getAttribute(\"data-href\")||c.getAttribute(\"href\");if(\"stylesheet\"===c.rel&&(u===t||u===r))return e()}var f=document.getElementsByTagName(\"style\");for(n=0;n<f.length;n++){var c;if((u=(c=f[n]).getAttribute(\"data-href\"))===t||u===r)return e()}var i=document.createElement(\"link\");i.rel=\"stylesheet\",i.type=\"text/css\",i.onload=e,i.onerror=function(e){var t=e&&e.target&&e.target.src||r,a=new Error(\"Loading CSS chunk \"+d+\" failed.\\n(\"+t+\")\");a.request=t,delete l[d],i.parentNode.removeChild(i),o(a)},i.href=r,document.getElementsByTagName(\"head\")[0].appendChild(i)}).then(function(){l[d]=0}));var a=s[d];if(0!==a)if(a)e.push(a[2]);else{var t=new Promise(function(e,t){a=s[d]=[e,t]});e.push(a[2]=t);var o,n=document.createElement(\"script\");n.charset=\"utf-8\",n.timeout=120,b.nc&&n.setAttribute(\"nonce\",b.nc),n.src=b.p+\"static/js/\"+({6:\"CSIRLayout\",7:\"Cat2Revise\",8:\"CatLayout\",9:\"Catlayout\",10:\"CmatLayout\",11:\"Completed\",12:\"Default\",13:\"DefaultRevise\",14:\"Duolingo\",15:\"GRERevise\",16:\"GRElayout\",17:\"IbpsLayout\",18:\"IbpsRevise\",19:\"MathGymLayout\",20:\"MobileReport\",21:\"NLATLayout\",22:\"NMATAdaptive\",23:\"NmatLayout\",24:\"PTELayout\",25:\"PageNotFound\",26:\"Report\",27:\"SatLayout\",28:\"SatLayoutNew\",29:\"SatRevise\",30:\"TokenPage\",31:\"XatLayout\",32:\"XatRevise\"}[d]||d)+\".\"+{0:\"5413d402\",1:\"6a5576af\",2:\"9f313ed6\",3:\"2adb1531\",4:\"920bd6f0\",5:\"239aa213\",6:\"4878cb39\",7:\"e479fb6b\",8:\"f46ad8c3\",9:\"26c0e34e\",10:\"824b308d\",11:\"c0e62643\",12:\"9ff8150e\",13:\"59615b34\",14:\"12bb87a9\",15:\"223ca1ee\",16:\"80f50820\",17:\"4b1f88c6\",18:\"a16759b7\",19:\"d80575bb\",20:\"cd7e6856\",21:\"b2094d5d\",22:\"dc284530\",23:\"595bd06e\",24:\"4dbaf84a\",25:\"35dcf3b2\",26:\"9f3818c2\",27:\"8519df5b\",28:\"b367d30b\",29:\"9d72a049\",30:\"bd892fd1\",31:\"7c8edb7c\",32:\"2427d082\",35:\"beda5dda\",36:\"75bf69e6\",38:\"a318b228\"}[d]+\".chunk.js\",o=function(e){n.onerror=n.onload=null,clearTimeout(u);var t=s[d];if(0!==t){if(t){var a=e&&(\"load\"===e.type?\"missing\":e.type),o=e&&e.target&&e.target.src,r=new Error(\"Loading chunk \"+d+\" failed.\\n(\"+a+\": \"+o+\")\");r.type=a,r.request=o,t[1](r)}s[d]=void 0}};var u=setTimeout(function(){o({type:\"timeout\",target:n})},12e4);n.onerror=n.onload=o,document.head.appendChild(n)}return Promise.all(e)},b.m=c,b.c=a,b.d=function(e,t,a){b.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:a})},b.r=function(e){\"undefined\"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:\"Module\"}),Object.defineProperty(e,\"__esModule\",{value:!0})},b.t=function(t,e){if(1&e&&(t=b(t)),8&e)return t;if(4&e&&\"object\"==typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(b.r(a),Object.defineProperty(a,\"default\",{enumerable:!0,value:t}),2&e&&\"string\"!=typeof t)for(var o in t)b.d(a,o,function(e){return t[e]}.bind(null,o));return a},b.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return b.d(t,\"a\",t),t},b.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},b.p=\"/\",b.oe=function(e){throw console.error(e),e};var t=window.webpackJsonp=window.webpackJsonp||[],o=t.push.bind(t);t.push=e,t=t.slice();for(var r=0;r<t.length;r++)e(t[r]);var p=o;i()}([])</script><script src=\"/static/js/37.891ee69e.chunk.js\"></script><script src=\"/static/js/main.491731fa.chunk.js\"></script></body></html>";
//
//	    public static void main(String[] args) {
//	        launch(args);
//	    }
//
//	    @Override
//	    public void start(Stage primaryStage) {
//	        WebView webView = new WebView();
//	        WebEngine webEngine = webView.getEngine();
//	        String htmlTemplate = identifyHtmlTemplate(htmlInput);
//
//	        String htmlContent = String.format(htmlTemplate, htmlInput);
//	        webEngine.loadContent(htmlContent);
//
//	        // Enable JavaScript execution in the WebView
//	        webEngine.setJavaScriptEnabled(true);
//
//	        // Add a listener to wait for the content to be loaded before applying styles
//	        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//	            if (newValue == Worker.State.SUCCEEDED) {
//	                // Apply CSS stylesheets to the WebView
//	                Document doc = webEngine.getDocument();
//	                Element head = (Element) doc.getElementsByTagName("head").item(0);
//	                Element style = doc.createElement("style");
//	                style.appendChild(doc.createTextNode(getCustomCSS()));
//	                head.appendChild(style);
//
//	                // Adjust WebView's height to fit the content
//	                Number scrollHeight = (Number) webEngine.executeScript("Math.max(document.documentElement.scrollHeight, document.body.scrollHeight)");
//	                webView.setPrefHeight(scrollHeight.doubleValue());	            }
//	        });
//
//	        primaryStage.setScene(new Scene(webView, 800, 600));
//	        primaryStage.setTitle("HTML to JavaFX");
//	        primaryStage.show();
//	    }
//
//	    private String identifyHtmlTemplate(String htmlContent) {
//	        // Find the opening and closing tags of the outermost element
//	        int openingTagIndex = htmlContent.indexOf("<");
//	        int closingTagIndex = htmlContent.lastIndexOf(">");
//
//	        if (openingTagIndex != -1 && closingTagIndex != -1) {
//	            // Extract the outermost element
//	            String outermostElement = htmlContent.substring(openingTagIndex, closingTagIndex + 1);
//
//	            // Build and return the template with the outermost element as the content placeholder
//	            return String.format(HTML_TEMPLATE, "%s");
//	        } else {
//	            // No outermost element found, use the default template
//	            return HTML_TEMPLATE;
//	        }
//	    }
//
//	    private String getCustomCSS() {
//	        StringBuilder cssBuilder = new StringBuilder();
//	        Pattern stylePattern = Pattern.compile("<style[^>]*>(.*?)</style>", Pattern.DOTALL);
//	        Matcher styleMatcher = stylePattern.matcher(htmlInput);
//
//	        while (styleMatcher.find()) {
//	            String styleContent = styleMatcher.group(1);
//	            cssBuilder.append(styleContent);
//	        }
//
//	        return cssBuilder.toString();
//	    }

}
