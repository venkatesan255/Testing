import argparse
import os
from datetime import datetime
from jmeter_metrics import version
from jmeter_metrics import generate_report


def parse_options():
    parser = argparse.ArgumentParser(formatter_class=argparse.ArgumentDefaultsHelpFormatter)

    parser.add_argument("-v", "--version", dest="version", action="store_true",
                        help="Version of the program")

    group = parser.add_argument_group("General")

    group.add_argument("-logo", "--logo", dest="logo", action="store_true", default="jmeter.png",
                       help="Logo to be displayed in report")

    group.add_argument('-s', '--source', dest='source', required=True, metavar='',
                       help="source file to be analysed. Supports *.jtl or *.csv files")

    group.add_argument('-o', '--output', dest='output_path', metavar='', default=os.path.curdir,
                       help="output file path for creating analysed metrics. Default current directory")

    group.add_argument("-m", "--metrics_report_name", dest="metrics_report_name", metavar='',
                       default='metrics-' + datetime.now().strftime('%Y%m%d-%H%M%S') + '.html',
                       help="Output name of the generated metrics report")

    args = parser.parse_args()
    return args


def main():
    args = parse_options()
    if args.version:
        print(version.__version__)
        exit(0)

    else:
        generate_report.generate_report(args)


if __name__ == '__main__':
    main()
