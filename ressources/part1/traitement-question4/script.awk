BEGIN {print "digraph automaton {";
    print "node [shape = circle];";
    print "rankdir=TD;";
    }
/C.*--/ {split($0,a) ;
    printf "%s -> %s [label=\"%s\"];\n", a[1], a[5], a[3] }
END {print "}"}
