import sys, random, subprocess

proc = subprocess.Popen(sys.argv[1:], stdin=subprocess.PIPE, stdout=subprocess.PIPE)
def p(x):
    proc.stdin.write(x+'\n')
    proc.stdin.flush()

wordlist=[]
f=open('wordlist.txt', 'r')
for i in f:
    wordlist.append(i[:-1] if i[-1]=='\n' else i)
# wordlist=[i[:-1] for i in f]
random.shuffle(wordlist)

score=0
totalerr=0

for s in wordlist:
    s2=[]
    for i in s:
        s2.append('_')
    err=0
    p(''.join(s2))
    while err<6 and '_' in s2:
        c=proc.stdout.readline().strip()
        nomatch=True
        for i in range(0, len(s)):
            if s[i]==c:
                s2[i]=c
                nomatch=False
        if nomatch:
            err+=1
            totalerr+=1
        p(''.join(s2))
    if err<6:
        score+=1
p('END')
sys.stderr.write('score is '+str(score)+', totalerr is '+str(totalerr)+'\n')