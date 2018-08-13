echo "CSharp"
Java -jar antlr.jar -Dlanguage=CSharp Shiba.g4 -o ../Windows/Shiba/Parser -package Shiba.Parser
echo "Java"
Java -jar antlr.jar -Dlanguage=Java Shiba.g4 -o ../Android/shiba/src/main/java/moe/tlaster/shiba/parser -package moe.tlaster.shiba.parser
echo "Swift"
Java -jar antlr.jar -Dlanguage=Swift Shiba.g4 -o ../iOS/Shiba/Shiba/Parser -package moe.tlaster.shiba.parser
echo "JavaScript"
Java -jar antlr.jar -Dlanguage=JavaScript Shiba.g4 -o ../React/src/parser
