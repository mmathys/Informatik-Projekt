package io.github.mmathys.Projekt.util;

import java.util.Random;

public class AnsiPrefixGenerator {
	public static final String[] errors = {
			  "(ノ͡° ͜ʖ ͡°)ノ︵┻┻"
			, "(ﾉಠдಠ)ﾉ︵┻━┻"
			, "ʕノ•ᴥ•ʔノ ︵ ┻━┻"
			, "┻━┻ ヘ╰( •̀ε•́ ╰)"
			, "༼⁰o⁰；༽"
			, "୧( ಠ Д ಠ )୨"
			, "o͡͡͡╮༼ • ʖ̯ • ༽╭o͡͡͡"
			, "└༼ ಥ ᗜ ಥ ༽┘"
			, "〳 ◔ Ĺ̯ ◔ 〵"
			, "╚═| ~ ಠ ₒ ಠ ~ |═╝"
			, "(╯=▃=)╯︵┻━┻"
			};
	public static final String[] happy = {
			  "(✿ ◕‿◕) ᓄ✂╰U╯"
			, "ᕕ( ՞ ᗜ ՞ )ᕗ"
			, "s( ^ ‿ ^)-b"
			, "໒( ͡ᵔ ▾ ͡ᵔ )७"
			, "ლ ( ◕  ᗜ  ◕ ) ლ"
			, "┌[ ◔ ͜ ʖ ◔ ]┐"
			, "ヽ〳 ՞ ᗜ ՞ 〵ง"
			, "░ ∗ ◕ ں ◕ ∗ ░"
			, "╰(◕ᗜ◕)╯"
	};
	
	public static final String[] sad = {
			  "o͡͡͡╮༼ • ʖ̯ • ༽╭o͡͡͡"
			, "( ຈ ﹏ ຈ )"
			, "ᕕ( ཀ ʖ̯ ཀ)ᕗ"
			, "(-_-｡)"
			, "໒( ❛ั Ĺ̯ ❛ั )७"
			, "| ” ☯ ︿ ☯ ” |"
			, "༼ ╥ ل ╥ ༽"
			, "⋋〳 ᵕ _ʖ ᵕ 〵⋌"
			, "┏༼ ◉ ╭╮ ◉༽┓"
	};
	
	public static final String[] sleep = {
			"(-, – )…zzzZZZ"
	};
	
	public static String getError() {
		return errors[new Random().nextInt(errors.length)];
	}
	
	public static String getHappy() {
		return happy[new Random().nextInt(happy.length)];
	}
	
	public static String getSad() {
		return sad[new Random().nextInt(sad.length)];
	}
	
	public static String getSleep() {
		return sleep[new Random().nextInt(sleep.length)];
	}
}
