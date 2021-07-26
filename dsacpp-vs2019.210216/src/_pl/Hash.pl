#
# Example of Perl hash
#
my	%hero = (  # identifier preceded by % holds a group of values, keyed by string
		"云长"=>"关羽",
		"翼德"=>"张飞",
		"子龙"=>"赵云",
		"孟起"=>"马超"
	);
foreach $style (keys %hero)
	{ print "  ($style,$hero{$style})"; }	print "\n";
$hero{"汉升"} = "黄忠";
foreach $style (keys %hero)
	{ print "  ($style,$hero{$style})"; }	print "\n";
foreach $style (sort keys %hero)
	{ print "  ($style,$hero{$style})"; }	print "\n";
foreach $style (reverse sort keys %hero)
	{ print "  ($style,$hero{$style})"; }	print "\n";