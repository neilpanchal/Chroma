package com.chroma;

public final class Args {

	public static void checkForRange(double input_, double low_, double high_, String errorString) {
		if (!isInRange(input_, low_, high_)) {
			throw new IllegalArgumentException(errorString);
		}
	}

	public static void checkForRange(double input_, double low_, double high_) {
		if (!isInRange(input_, low_, high_)) {
			throw new IllegalArgumentException(input_ + " not in range " + low_
					+ ".." + high_);
		}
	}

	public static void checkForLowHigh(double low_, double high_) {
		if (!(low_ < high_)) {
			throw new IllegalArgumentException(low_ + " is larger than "
					+ high_
					+ ". Please make sure the Min & Max values are valid.");
		}
	}

	public static void checkForNull(Object aObject) {
		if (aObject == null) {
			throw new NullPointerException();
		}
	}

	public static void checkForPositive(int input_) {
		if (!(input_ > 0)) {
			throw new IllegalArgumentException(input_
					+ " is negative. Please enter a positive number.");
		}
	}

	public static boolean isInRange(int input_, int low_, int high_) {
		return (input_ >= low_) && (input_ <= high_);
	}

	public static boolean isInRange(double input_, double low_, double high_) {
		return (input_ >= low_) && (input_ <= high_);
	}

	// PRIVATE
	private Args() {
		// empty - prevent construction
	}
}
