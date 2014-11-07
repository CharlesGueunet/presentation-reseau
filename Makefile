###############################################################################
# name:	    | Makefile
# @uthor:   | Axel <axel.martin@eisti.fr> Martin
# function: | Makefile pour le pdf de la coding night
# language: | French
# licence:  | Free
# more:     | -
###############################################################################


# Variables de compilation
#######################################

PDFOUTPUT = ./presentation.pdf
ALLLATEX = $(shell find . -name "*.tex")
LATEXOPTS = 


# Compilation du document
#######################################

all: $(PDFOUTPUT) clean


%.pdf: %.tex $(ALLLATEX)
	latex $(LATEXOPTS) $<
	$(ifneq ("$(wildcard $(@:.tex=.bib))",""), bibtex $(<:.tex=.aux))
	latex $(LATEXOPTS) $<
	latex $(LATEXOPTS) $<
	dvips $(<:.tex=.dvi) -o $(<:.tex=.ps)
	ps2pdf $(<:.tex=.ps) $@


clean:
	rm -f $(shell find . -regextype posix-extended -regex '.*\.(aux|bbl|blg|toc|log|dvi|ps|snm|nav|out)')
